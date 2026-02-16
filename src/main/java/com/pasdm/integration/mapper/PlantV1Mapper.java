package com.pasdm.integration.mapper;

import com.pasdm.integration.model.PlantV1;
import com.pasdm.integration.util.ExcelValueParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@Slf4j
@Component
public class PlantV1Mapper {

    public PlantV1 mapEntity(Map<Integer, String> row) {
        try {
            LocalDate localDate = LocalDate.now();
            PlantV1 e = new PlantV1();
            if (ExcelValueParser.dateValidador(row.get(1)) != null) {

                if (ExcelValueParser.dateValidador(row.get(1)).isBefore(localDate) || ExcelValueParser.dateValidador(row.get(1)).isEqual(localDate)) {

                    e.setFecha1(ExcelValueParser.dateValidador(row.get(1)));

            /* =======================
               BLOQUE MINERAL OXIDOS PARTE 1
               ======================= */
                    e.setToneladasMilOxidos(ExcelValueParser.decimalValidador(row.get(2)));
                    e.setLeyAuOxidos(ExcelValueParser.decimalValidador(row.get(3)));
                    e.setLeyAgOxidos(ExcelValueParser.decimalValidador(row.get(4)));
                    e.setRecuperacionAuOxidosPct(ExcelValueParser.decimalValidador(row.get(5)));
                    e.setRecuperacionAgOxidosPct(ExcelValueParser.decimalValidador(row.get(6)));

            /* =======================
               BLOQUE MINERAL SULFUROS PARTE 1
               ======================= */
                    e.setToneladasMilSulfuros(ExcelValueParser.decimalValidador(row.get(7)));
                    e.setLeyAuSulfuros(ExcelValueParser.decimalValidador(row.get(8)));
                    e.setLeyAgSulfuros(ExcelValueParser.decimalValidador(row.get(9)));
                    e.setLeyPbSulfuros(ExcelValueParser.decimalValidador(row.get(10)));
                    e.setLeyZnSulfuros(ExcelValueParser.decimalValidador(row.get(11)));
                    e.setRecuperacionAuSulfurosPct(ExcelValueParser.decimalValidador(row.get(12)));
                    e.setRecuperacionAgSulfurosPct(ExcelValueParser.decimalValidador(row.get(13)));
                    e.setRecuperacionPbSulfurosPct(ExcelValueParser.decimalValidador(row.get(14)));
                    e.setRecuperacionZnSulfurosPct(ExcelValueParser.decimalValidador(row.get(15)));

            /* =======================
               BLOQUE STOCK
               ======================= */
                    e.setStockSulfurosTm(ExcelValueParser.decimalValidador(row.get(16)));
                    e.setStockOxidosTm(ExcelValueParser.decimalValidador(row.get(17)));
                    e.setToneladasMilTotal(ExcelValueParser.decimalValidador(row.get(18)));

            /* =======================
               BLOQUE OXIDOS
               ======================= */
                    e.setAuOxidosOz(ExcelValueParser.decimalValidador(row.get(19)));
                    e.setAgOxidosOz(ExcelValueParser.decimalValidador(row.get(20)));

            /* =======================
               BLOQUE SULFUROS
               ======================= */
                    e.setAuSulfurosOz(ExcelValueParser.decimalValidador(row.get(21)));
                    e.setAgSulfurosOz(ExcelValueParser.decimalValidador(row.get(22)));
                    e.setPbSulfurosT(ExcelValueParser.decimalValidador(row.get(23)));
                    e.setZnSulfurosT(ExcelValueParser.decimalValidador(row.get(24)));

            /* =======================
               BLOQUE CONSOLIDADO
               ======================= */
                    e.setAuConsolidadoOz(ExcelValueParser.decimalValidador(row.get(25)));
                    e.setAgConsolidadoOz(ExcelValueParser.decimalValidador(row.get(26)));
                    e.setPbConsolidadoT(ExcelValueParser.decimalValidador(row.get(27)));
                    e.setZnConsolidadoT(ExcelValueParser.decimalValidador(row.get(28)));

            /* =======================
               BLOQUE LEY CONSOLIDADO
               ======================= */
                    e.setLeyAuConsolidado(ExcelValueParser.decimalValidador(row.get(29)));
                    e.setLeyAgConsolidado(ExcelValueParser.decimalValidador(row.get(30)));

            /* =======================
               BLOQUE RECUPERACION CONSOLIDADO
               ======================= */
                    e.setRecuperacionAuConsolidadoPct(ExcelValueParser.decimalValidador(row.get(31)));
                    e.setRecuperacionAgConsolidadoPct(ExcelValueParser.decimalValidador(row.get(32)));

            /* =======================
               BLOQUE PROCESO
               ======================= */
                    e.setCianuroKg(ExcelValueParser.decimalValidador(row.get(33)));
                    e.setAntiincrustanteKg(ExcelValueParser.decimalValidador(row.get(34)));
                    e.setCalKg(ExcelValueParser.decimalValidador(row.get(35)));
                    e.setBolasMoliendaKg(ExcelValueParser.decimalValidador(row.get(369)));
                    e.setDepreZincKg(ExcelValueParser.decimalValidador(row.get(37)));

            /* =======================
               BLOQUE SERVICIOS
               ======================= */
                    e.setElectricidadKw(ExcelValueParser.decimalValidador(row.get(38)));
                    e.setAguaM3(ExcelValueParser.decimalValidador(row.get(39)));

            /* =======================
               BLOQUE DISPONIBILIDAD PLANTA
               ======================= */
                    e.setDisponibilidadPlantaOxidosHrs(ExcelValueParser.decimalValidador(row.get(40)));
                    e.setDisponibilidadPlantaSulfurosHrs(ExcelValueParser.decimalValidador(row.get(41)));

            /* =======================
               BLOQUE USO PLANTA
               ======================= */
                    e.setUsoPlantaOxidosHrs(ExcelValueParser.decimalValidador(row.get(42)));
                    e.setUsoPlantaSulfurosHrs(ExcelValueParser.decimalValidador(row.get(43)));

                    return e;
                }
            }
        } catch (Exception e) {
            log.error("Error procesando Excel", e);
        }
        return null;
    }

}
