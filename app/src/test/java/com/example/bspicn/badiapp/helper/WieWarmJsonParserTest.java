package com.example.bspicn.badiapp.helper;

import com.example.bspicn.badiapp.model.Badi;
import com.example.bspicn.badiapp.model.Becken;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class WieWarmJsonParserTest {

    String jsonBadi = " \"badid\": 1,\n" +
            "  \"badname\": \"Frei- und Hallenbad\",\n" +
            "  \"kanton\": \"BE\",\n" +
            "  \"plz\": \"3400\",\n" +
            "  \"ort\": \"Burgdorf\",\n" +
            "  \"adresse1\": \"Freibad\",\n" +
            "  \"adresse2\": \"\",\n" +
            "  \"email\": \"info@hallenbad-burgdorf.ch\",\n" +
            "  \"telefon\": \"0344222765\",\n" +
            "  \"www\": null,\n" +
            "  \"long\": 7375244,\n" +
            "  \"lat\": 47031558,\n" +
            "  \"zeiten\": \"12. Mai bis 17. Juni\\nMontag bis Freitag       9:00-20:00 Uhr\\nSamstag und Sonntag      9:00-19:00 Uhr\\n\\n\\n18. Juni bis 12. August\\nMontag bis Freitag\\t  9:00-20:30 Uhr\\nSamstag und Sonntag       9:00-19:00 Uhr\\n\\n\\n13. August bis 16. September\\nMontag bis Freitag\\t  9:00-19:30 Uhr\\nSamstag und Sonntag       9:00-19:00 Uhr\\n\\n\\nFrühschwimmen am Freitag :\\nBei gutem Wetter vom 13. Juli bis 10. August ist das Bad bereits ab 07:00 Uhr geöffnet\\n\\nNachtschwimmen :\\nWird spontan bei gutem Wetter statt finden.\\n\\n\\nAn der Solennität 27.06.16 bleibt das Freibad den ganzen Tag geschlossen.\\n\\n\\nBei schlechtem Wetter kann die Schliesszeit vorverlegt werden oder\\ndie Anlage kann den ganzen Tag geschlossen bleiben.\",\n" +
            "  \"preise\": \"Erwachsene :             SFr. 5.- / Saison: Fr. 65.-\\nStudenten / AHV / IV :   SFr. 4.- / Saison: Fr. 45.-\\nSchüler :                SFr. 2.- / Saison: Fr. 25.-\\nChipkarte:               SFr. 5.-  (Verkauf)\",\n" +
            "  \"info\": \"\",\n" +
            "  \"wetterort\": \"Bern\",\n" +
            "  \"uv_station_name\": \"Bern\",\n" +
            "  \"uv_wert\": 4,\n" +
            "  \"uv_date\": \"2019-05-27 00:00:00\",\n" +
            "  \"uv_date_pretty\": \"27.05.\",\n" +
            "  \"becken\": {\n" +
            "    \"Schwimmbecken\": {\n" +
            "      \"beckenid\": 1,\n" +
            "      \"beckenname\": \"Schwimmbecken\",\n" +
            "      \"temp\": \"20.5\",\n" +
            "      \"date\": \"2019-05-25 13:44:03\",\n" +
            "      \"typ\": \"Freibad\",\n" +
            "      \"status\": \"geöffnet\",\n" +
            "      \"smskeywords\": \";BURGDORF;\",\n" +
            "      \"smsname\": \"Burgdorf Freibad\",\n" +
            "      \"ismain\": \"T   \",\n" +
            "      \"date_pretty\": \"25.05.\"\n" +
            "    },\n" +
            "    \"Hallenbad\": {\n" +
            "      \"beckenid\": 127,\n" +
            "      \"beckenname\": \"Hallenbad\",\n" +
            "      \"temp\": \"27.0\",\n" +
            "      \"date\": \"2019-05-25 13:44:03\",\n" +
            "      \"typ\": \"Hallenbad\",\n" +
            "      \"status\": \"geöffnet\",\n" +
            "      \"smskeywords\": null,\n" +
            "      \"smsname\": null,\n" +
            "      \"ismain\": \"T   \",\n" +
            "      \"date_pretty\": \"25.05.\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"bilder\": [\n" +
            "    {\n" +
            "      \"image\": \"img/baeder/1/1.jpg\",\n" +
            "      \"text\": \"\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"image\": \"img/baeder/1/2.jpg\",\n" +
            "      \"text\": \"Beschreibung\\n\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"image\": \"img/baeder/1/3.jpg\",\n" +
            "      \"text\": \"\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"image\": \"img/baeder/1/4.jpg\",\n" +
            "      \"text\": \"\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"image\": \"img/baeder/1/5.jpg\",\n" +
            "      \"text\": \"\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"wetter\": [\n" +
            "    {\n" +
            "      \"wetter_symbol\": 10,\n" +
            "      \"wetter_temp\": \"20.0\",\n" +
            "      \"wetter_date\": \"2019-05-27 00:00:00\",\n" +
            "      \"wetter_date_pretty\": \"27.05.\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"wetter_symbol\": 5,\n" +
            "      \"wetter_temp\": \"19.0\",\n" +
            "      \"wetter_date\": \"2019-05-26 00:00:00\",\n" +
            "      \"wetter_date_pretty\": \"26.05.\"\n" +
            "    }\n" +
            "  ]"

    @Test
    public void createBadiFromJsonString() {
        Badi badi = WieWarmJsonParser.createBadiFromJsonString(jsonBadi);
        Assert.assertTrue(badi.equals());
    }

    public Badi createTestBadi() {
        Badi badi = new Badi();
        badi.setId(1);
        badi.setName("Frei- und Hallenbad");
        badi.setKanton("BE");
        badi.setOrt("Burgdorf");
        Becken becken = new Becken();
        becken.setTemperature(20.5);
        becken.setName("Schwimmbecken");

        badi.addBecken(becken);


        return badi;
    }
}