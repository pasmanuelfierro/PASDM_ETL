package com.pasdm.etl.mapper;

import com.pasdm.etl.model.GeologyGrade;
import com.pasdm.etl.util.ExcelValueParser;
import com.pasdm.etl.util.HashUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class GeologyGradeMapper {

    public static final int COL_FECHA = 0;
    public static final int COL_MINA = 1;
    public static final int COL_AREA = 2;
    public static final int COL_M_MINADO = 3;
    public static final int COL_MINERAL = 4;
    public static final int COL_LUGAR = 5;
    public static final int COL_ML = 6;
    public static final int COL_ANCHO_PLANEADO = 7;
    public static final int COL_PLAN = 8;
    public static final int COL_VETA = 9;

    public static final int COL_TON_PLAN2 = 10;
    public static final int COL_TON_PLAN3 = 11;
    public static final int COL_CONCESION = 12;
    public static final int COL_AG_PLAN = 13;
    public static final int COL_AU_PLAN = 14;
    public static final int COL_PB_PLAN = 15;
    public static final int COL_ZN_PLAN = 16;

    public static final int COL_TON_PINTARRON = 17;
    public static final int COL_TON_MANTEO = 18;
    public static final int COL_ANCHO_REAL = 19;
    public static final int COL_ANCHO_MUESTREO = 20;

    public static final int COL_LEY_AG = 21;
    public static final int COL_LEY_AU = 22;
    public static final int COL_LEY_PB = 23;
    public static final int COL_LEY_ZN = 24;

    public static final int COL_LINEA_REZ = 25;
    public static final int COL_CLASS = 26;
    public static final int COL_DIL_PISO = 27;
    public static final int COL_DILUCION = 28;

    public static final int COL_AG_DIL = 29;
    public static final int COL_AU_DIL = 30;
    public static final int COL_PB_DIL = 31;
    public static final int COL_ZN_DIL = 32;

    public static final int COL_AG_TON_DIL = 33;
    public static final int COL_AU_TON_DIL = 34;
    public static final int COL_PB_TON_DIL = 35;
    public static final int COL_ZN_TON_DIL = 36;

    public static final int COL_AG_08_DIL = 37;
    public static final int COL_AU_08_DIL = 38;
    public static final int COL_PB_08_DIL = 39;
    public static final int COL_ZN_08_DIL = 40;

    public static final int COL_VPT_TAB = 41;
    public static final int COL_VPT_TONS = 42;
    public static final int COL_TON_PLAN_AG = 43;
    public static final int COL_TON_PLAN_AU = 44;
    public static final int COL_TON_PLAN_PB = 45;
    public static final int COL_TON_PLAN_ZN = 46;

    public static final int COL_VPT_PLAN = 47;
    public static final int COL_VPT_TONS_PLAN = 48;
    public static final int COL_CUMPLIMIENTO_TONS = 49;
    public static final int COL_CUMPLI_LEY_AG = 50;

    public static final int COL_FC = 51;
    public static final int COL_DIF_AG = 52;
    public static final int COL_DIF_AU = 53;
    public static final int COL_DIF_PB = 54;
    public static final int COL_DIF_ZN = 55;

    public static final int COL_ANCHO_PROGRAMADO = 56;
    public static final int COL_DILPISO_TON = 57;
    public static final int COL_DIL_TON = 58;
    public static final int COL_DIL_ANCHO_MIN = 59;

    public static final int COL_COLUMNA1 = 60;
    public static final int COL_MIN_CLAVE = 61;
    public static final int COL_TIPO_MINADO = 62;
    public static final int COL_CLAVE = 63;
    public static final int COL_OBRA_VETA = 64;
    public static final int COL_M_REAL = 65;

    public GeologyGrade mapEntity(Map<Integer, String> row) {
        GeologyGrade e = new GeologyGrade();
        try {
            if (ExcelValueParser.dateValidador(row.get(COL_FECHA)) != null) {
                e.setFecha(ExcelValueParser.dateValidador(row.get(COL_FECHA)));
                e.setMina(row.get(COL_MINA));
                e.setArea(row.get(COL_AREA));
                e.setMMinado(row.get(COL_M_MINADO));
                e.setMineral(row.get(COL_MINERAL));
                e.setLugar(row.get(COL_LUGAR));
                e.setMl(ExcelValueParser.decimalValidador(row.get(COL_ML)));
                e.setAnchoPlaneado(ExcelValueParser.decimalValidador(row.get(COL_ANCHO_PLANEADO)));
                e.setPlan(row.get(COL_PLAN));
                e.setVeta(row.get(COL_VETA));

                e.setTonPlan2(ExcelValueParser.decimalValidador(row.get(COL_TON_PLAN2)));
                e.setTonPlan3(ExcelValueParser.decimalValidador(row.get(COL_TON_PLAN3)));
                e.setConcesion(row.get(COL_CONCESION));
                e.setAgPlan(ExcelValueParser.decimalValidador(row.get(COL_AG_PLAN)));
                e.setAuPlan(ExcelValueParser.decimalValidador(row.get(COL_AU_PLAN)));
                e.setPbPlan(ExcelValueParser.decimalValidador(row.get(COL_PB_PLAN)));
                e.setZnPlan(ExcelValueParser.decimalValidador(row.get(COL_ZN_PLAN)));

                e.setTonPintarron(ExcelValueParser.decimalValidador(row.get(COL_TON_PINTARRON)));
                e.setTonManteo(ExcelValueParser.decimalValidador(row.get(COL_TON_MANTEO)));
                e.setAnchoReal(ExcelValueParser.decimalValidador(row.get(COL_ANCHO_REAL)));
                e.setAnchoMuestreo(ExcelValueParser.decimalValidador(row.get(COL_ANCHO_MUESTREO)));

                e.setLeyAg(ExcelValueParser.decimalValidador(row.get(COL_LEY_AG)));
                e.setLeyAu(ExcelValueParser.decimalValidador(row.get(COL_LEY_AU)));
                e.setLeyPb(ExcelValueParser.decimalValidador(row.get(COL_LEY_PB)));
                e.setLeyZn(ExcelValueParser.decimalValidador(row.get(COL_LEY_ZN)));

                e.setLineaRez(row.get(COL_LINEA_REZ));
                e.setClase(row.get(COL_CLASS));
                e.setDilPiso(ExcelValueParser.decimalValidador(row.get(COL_DIL_PISO)));
                e.setDilucion(ExcelValueParser.decimalValidador(row.get(COL_DILUCION)));

                e.setAgDil(ExcelValueParser.decimalValidador(row.get(COL_AG_DIL)));
                e.setAuDil(ExcelValueParser.decimalValidador(row.get(COL_AU_DIL)));
                e.setPbDil(ExcelValueParser.decimalValidador(row.get(COL_PB_DIL)));
                e.setZnDil(ExcelValueParser.decimalValidador(row.get(COL_ZN_DIL)));

                e.setAgTonDil(ExcelValueParser.decimalValidador(row.get(COL_AG_TON_DIL)));
                e.setAuTonDil(ExcelValueParser.decimalValidador(row.get(COL_AU_TON_DIL)));
                e.setPbTonDil(ExcelValueParser.decimalValidador(row.get(COL_PB_TON_DIL)));
                e.setZnTonDil(ExcelValueParser.decimalValidador(row.get(COL_ZN_TON_DIL)));

                e.setAg08Dil(ExcelValueParser.decimalValidador(row.get(COL_AG_08_DIL)));
                e.setAu08Dil(ExcelValueParser.decimalValidador(row.get(COL_AU_08_DIL)));
                e.setPb08Dil(ExcelValueParser.decimalValidador(row.get(COL_PB_08_DIL)));
                e.setZn08Dil(ExcelValueParser.decimalValidador(row.get(COL_ZN_08_DIL)));

                e.setVptTab(ExcelValueParser.decimalValidador(row.get(COL_VPT_TAB)));
                e.setVptTons(ExcelValueParser.decimalValidador(row.get(COL_VPT_TONS)));
                e.setTonPlanAg(ExcelValueParser.decimalValidador(row.get(COL_TON_PLAN_AG)));
                e.setTonPlanAu(ExcelValueParser.decimalValidador(row.get(COL_TON_PLAN_AU)));
                e.setTonPlanPb(ExcelValueParser.decimalValidador(row.get(COL_TON_PLAN_PB)));
                e.setTonPlanZn(ExcelValueParser.decimalValidador(row.get(COL_TON_PLAN_ZN)));

                e.setVptPlan(ExcelValueParser.decimalValidador(row.get(COL_VPT_PLAN)));
                e.setVptTonsPlan(ExcelValueParser.decimalValidador(row.get(COL_VPT_TONS_PLAN)));
                e.setCumplimiento(ExcelValueParser.decimalValidador(row.get(COL_CUMPLIMIENTO_TONS)));
                e.setCumpliLeyAg(ExcelValueParser.decimalValidador(row.get(COL_CUMPLI_LEY_AG)));

                e.setFc(ExcelValueParser.decimalValidador(row.get(COL_FC)));
                e.setDifAg(ExcelValueParser.decimalValidador(row.get(COL_DIF_AG)));
                e.setDifAu(ExcelValueParser.decimalValidador(row.get(COL_DIF_AU)));
                e.setDifPb(ExcelValueParser.decimalValidador(row.get(COL_DIF_PB)));
                e.setDifZn(ExcelValueParser.decimalValidador(row.get(COL_DIF_ZN)));

                e.setAnchoProgramado(ExcelValueParser.decimalValidador(row.get(COL_ANCHO_PROGRAMADO)));
                e.setDilPisoTon(ExcelValueParser.decimalValidador(row.get(COL_DILPISO_TON)));
                e.setDilTon(ExcelValueParser.decimalValidador(row.get(COL_DIL_TON)));
                e.setDilAnchoMin(ExcelValueParser.decimalValidador(row.get(COL_DIL_ANCHO_MIN)));

                e.setColumna1(row.get(COL_COLUMNA1));
                e.setMinClave(row.get(COL_MIN_CLAVE));
                e.setTipoMinado(row.get(COL_TIPO_MINADO));
                e.setClave(row.get(COL_CLAVE));
                e.setObraVeta(row.get(COL_OBRA_VETA));
                e.setMReal(ExcelValueParser.decimalValidador(row.get(COL_M_REAL)));

                e.setRowHash(HashUtil.calculateRowHash(e.getFecha().toString(), e.getPlan(), e.getLugar(), e.getVeta(),e.getObraVeta(),""));
                return e;
            } else {
                return null;
            }
        } catch (Exception ex) {
            log.error("Error procesando Excel GeologyGrade", ex);
            return null;
        }
    }
}
