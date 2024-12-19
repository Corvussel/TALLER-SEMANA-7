package com.lab6.crud_lab6.controller;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.lab6.crud_lab6.model.Empleado;
import com.lab6.crud_lab6.service.EmpleadoService;

import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping()
    public String Main(Model model) {
        List<Empleado> empleados = empleadoService.obtenerEmpleados();
        model.addAttribute("empleados", empleados);
        model.addAttribute("empleado", new Empleado());
        return "empleados";
    }

    @GetMapping("/listaEmpleados")
    public String showEmpleados(Model model) {
        List<Empleado> empleados = empleadoService.obtenerEmpleados();
        model.addAttribute("empleados", empleados);
        model.addAttribute("empleado", new Empleado());
        return "empleados";
    }

    @PostMapping("/RegistrarEmpleado")
    public String registrarEmpleado(@ModelAttribute Empleado empleado, Model model) {
        empleadoService.registrarEmpleado(empleado);
        model.addAttribute("empleado", empleado);
        return "redirect:/listaEmpleados";
    }

    @GetMapping("/EditarEmpleado/{id}")
    public String editarEmpleado(@PathVariable("id") Long id, Model model) {
        Optional<Empleado> empleado = empleadoService.buscarEmpleado(id);
        if (empleado.isPresent()) {
            model.addAttribute("empleado", empleado.get());
            return "empleado";
        } else {
            return "redirect:/listaEmpleados";
        }
    }

    @PostMapping("/GuardarEdicion")
    public String guardarEdicion(@ModelAttribute Empleado empleado) {
        empleadoService.editarEmpleado(empleado);
        return "redirect:/listaEmpleados";
    }

    @GetMapping("/EliminarEmpleado/{id}")
    public String eliminarEmpleado(@PathVariable("id") Long id) {
        empleadoService.eliminarEmpleado(id);
        return "redirect:/listaEmpleados";
    }

    @GetMapping("/reporte/pdf")
    public void generarReporte(HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=reporte.pdf");

        PdfWriter writer = new PdfWriter(response.getOutputStream());

        Document document = new Document(new PdfDocument(writer));

        document.add(new Paragraph("Reporte de Empleados").setBold().setFontSize(18));

        Table table = new Table(4);
        table.addCell("ID");
        table.addCell("Nombre");
        table.addCell("Numero Celular");
        table.addCell("Email");

        empleadoService.obtenerEmpleados().forEach(empleado -> {
            table.addCell(empleado.getId().toString());
            table.addCell(empleado.getNombre());
            table.addCell(empleado.getNumeroCelular());
            table.addCell(empleado.getEmail());
        });

        document.add(table);
        document.close();
    }

    @GetMapping("/reporte/excel")
    public String generarReporteExcel(HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=empleado_reporte.xls");

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Empleados");

        Row header = sheet.createRow(0);
        String[] headers = { "ID", "Nombre", "Numero Celular", "Email" };
        for (int i = 0; i < headers.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(headers[i]);
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            cell.setCellStyle(headerStyle);
        }

        int rowNum = 1;
        for (Empleado empleado : empleadoService.obtenerEmpleados()) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(empleado.getId());
            row.createCell(1).setCellValue(empleado.getNombre());
            row.createCell(2).setCellValue(empleado.getNumeroCelular());
            row.createCell(3).setCellValue(empleado.getEmail());
        }

        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(response.getOutputStream());
        workbook.close();

        return "redirect:/listaEmpleados";
    }

}
