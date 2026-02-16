package com.pasdm.integration.mapper;

import com.pasdm.integration.model.GeologyReport;
import com.pasdm.integration.util.ExcelValueParser;
import com.pasdm.integration.util.HashUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class GeologyReportMapper {

    public static final int COL_FECHA = 0;
    public static final int COL_TURNO = 1;
    public static final int COL_MINA = 2;
    public static final int COL_ZONA = 3;
    public static final int COL_TIPO = 4;
    public static final int COL_VETA = 5;
    public static final int COL_LUGAR = 6;
    public static final int COL_ESTATUS = 7;
    public static final int COL_COMENTARIO = 8;
    public static final int COL_PROY_MINERAL = 9;
    public static final int COL_LINEA_REZ = 10;
    public static final int COL_ANCHO_VETA = 11;
    public static final int COL_ANCHO_PROGRAMADO = 12;
    public static final int COL_ANCHO_OBRA = 13;
    public static final int COL_DILUCION = 14;
    public static final int COL_AG1 = 15;
    public static final int COL_AU1 = 16;
    public static final int COL_PB1 = 17;
    public static final int COL_ZN1 = 18;
    public static final int COL_BLOCK_RESERVAS = 19;
    public static final int COL_LEY_BLOCK = 20;
    public static final int COL_AG = 21;
    public static final int COL_AU = 22;
    public static final int COL_PB = 23;
    public static final int COL_ZN = 24;
    public static final int COL_VPT = 25;
    public static final int COL_AG_PLAN_DIL_0 = 26;
    public static final int COL_DIL_PLAN = 27;
    public static final int COL_DIL_PISO = 28;
    public static final int COL_DIL_PISO_2 = 29;

    public GeologyReport mapEntity(Map<Integer, String> row) {
        GeologyReport e = new GeologyReport();
        try {
            if (ExcelValueParser.dateValidador(row.get(COL_FECHA)) != null) {
                e.setFecha(ExcelValueParser.dateValidador(row.get(COL_FECHA)));
                e.setTurno(row.get(COL_TURNO));
                e.setMina(row.get(COL_MINA));
                e.setZona(row.get(COL_ZONA));
                e.setTipo(row.get(COL_TIPO));
                e.setVeta(row.get(COL_VETA));
                e.setLugar(row.get(COL_LUGAR));
                e.setEstatus(row.get(COL_ESTATUS));
                e.setComentario(row.get(COL_COMENTARIO));
                e.setProyMineral(row.get(COL_PROY_MINERAL));
                e.setLineaRez(ExcelValueParser.decimalValidador(row.get(COL_LINEA_REZ)));
                e.setAnchoVeta(ExcelValueParser.decimalValidador(row.get(COL_ANCHO_VETA)));
                e.setAnchoProgramado(ExcelValueParser.decimalValidador(row.get(COL_ANCHO_PROGRAMADO)));
                e.setAnchoObra(ExcelValueParser.decimalValidador(row.get(COL_ANCHO_OBRA)));
                e.setDilucion(ExcelValueParser.decimalValidador(row.get(COL_DILUCION)));
                e.setAg1(ExcelValueParser.decimalValidador(row.get(COL_AG1)));
                e.setAu1(ExcelValueParser.decimalValidador(row.get(COL_AU1)));
                e.setPb1(ExcelValueParser.decimalValidador(row.get(COL_PB1)));
                e.setZn1(ExcelValueParser.decimalValidador(row.get(COL_ZN1)));
                e.setBlockReservas(row.get(COL_BLOCK_RESERVAS));
                e.setLeyBlock(ExcelValueParser.decimalValidador(row.get(COL_LEY_BLOCK)));
                e.setAg(ExcelValueParser.decimalValidador(row.get(COL_AG)));
                e.setAu(ExcelValueParser.decimalValidador(row.get(COL_AU)));
                e.setPb(ExcelValueParser.decimalValidador(row.get(COL_PB)));
                e.setZn(ExcelValueParser.decimalValidador(row.get(COL_ZN)));
                e.setVpt(ExcelValueParser.decimalValidador(row.get(COL_VPT)));
                e.setAgPlanDil0(ExcelValueParser.decimalValidador(row.get(COL_AG_PLAN_DIL_0)));
                e.setDilPlan(ExcelValueParser.decimalValidador(row.get(COL_DIL_PLAN)));
                e.setDilPiso(ExcelValueParser.decimalValidador(row.get(COL_DIL_PISO)));
                e.setDilPiso2(ExcelValueParser.decimalValidador(row.get(COL_DIL_PISO_2)));

                e.setRowHash(HashUtil.calculateRowHash(e.getFecha().toString(), e.getTurno(), e.getLugar(), e.getVeta(),e.getTipo(),e.getEstatus()));
                return e;
            } else {
                return null;
            }
        } catch (Exception ex) {
            log.error("Error procesando Excel GeologyReport", ex);
            return null;
        }
    }
}
