package com.pasdm.etl.mapper;

import com.pasdm.etl.model.RRHH;
import com.pasdm.etl.util.ExcelValueParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@Slf4j
@Component
public class RRHHMapper {

    public RRHH mapEntity(Map<Integer, String> row) {
        try {
            LocalDate localDate = LocalDate.now();

            RRHH e = new RRHH();
            if (ExcelValueParser.dateValidador(row.get(1)) != null) {

                if (ExcelValueParser.dateValidador(row.get(1)).isBefore(localDate) || ExcelValueParser.dateValidador(row.get(1)).isEqual(localDate)) {

                    e.setFecha(ExcelValueParser.dateValidador(row.get(1)));
                    /* =======================
                       ASISTENCIA
                       ======================= */
                    e.setAsistenciaPersonal(ExcelValueParser.intValidador(row.get(2)));
                    e.setVacaciones(ExcelValueParser.intValidador(row.get(3)));
                    e.setDescanso(ExcelValueParser.intValidador(row.get(4)));
                    e.setFalta(ExcelValueParser.intValidador(row.get(5)));
                    e.setIncapacidad(ExcelValueParser.intValidador(row.get(6)));
                    e.setPermisoConGoce(ExcelValueParser.intValidador(row.get(7)));
                    e.setPermisoSinGoce(ExcelValueParser.intValidador(row.get(8)));
                    e.setAsistenciaContratistas(ExcelValueParser.intValidador(row.get(9)));

                    /* =======================
                       CAPACITACION
                       ======================= */
                    e.setOperacionMina(ExcelValueParser.intValidador(row.get(11)));
                    e.setOtrasAreas(ExcelValueParser.intValidador(row.get(12)));
                    e.setInduccion(ExcelValueParser.intValidador(row.get(13)));

                    /* =======================
                       VERIFICACIONES CRM
                       ======================= */
                    e.setFechaVerificacion(ExcelValueParser.dateValidador(row.get(15)));
                    e.setVerificacionSupervisor(ExcelValueParser.intValidador(row.get(16)));
                    e.setVerificacionOperativo(ExcelValueParser.intValidador(row.get(17)));

                    e.setFechaCrm(ExcelValueParser.dateValidador(row.get(19)));
                    e.setComentario(ExcelValueParser.stringValidador(row.get(20)));
                    e.setImagenes(ExcelValueParser.stringValidador(row.get(21)));
                    return e;
                }
            }
        } catch (Exception e) {
            log.error("Error procesando Excel RRHH", e);
        }
        return null;
    }

}
