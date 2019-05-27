package com.example.bspicn.badiapp.helper;

import com.example.bspicn.badiapp.model.Badi;
import com.example.bspicn.badiapp.model.Becken;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;


public class WieWarmJsonParserTest {

    String jsonBadi = "{\"badid\": \"1\",\"badname\": \"Frei- und Hallenbad\",\"kanton\": \"BE\",\"plz\": \"3400\",\"ort\": \"Burgdorf\",\"adresse1\": \"Freibad\",\"adresse2\": \"\",\"email\": \"info@hallenbad-burgdorf.ch\",\"telefon\": \"0344222765\",\"www\": null,\"long\": 7375244,\"lat\": 47031558,\"zeiten\": \"12. Mai bis 17. Juni\\nMontag bis Freitag       9:00-20:00 Uhr\\nSamstag und Sonntag      9:00-19:00 Uhr\\n\\n\\n18. Juni bis 12. August\\nMontag bis Freitag\\t  9:00-20:30 Uhr\\nSamstag und Sonntag       9:00-19:00 Uhr\\n\\n\\n13. August bis 16. September\\nMontag bis Freitag\\t  9:00-19:30 Uhr\\nSamstag und Sonntag       9:00-19:00 Uhr\\n\\n\\nFrühschwimmen am Freitag :\\nBei gutem Wetter vom 13. Juli bis 10. August ist das Bad bereits ab 07:00 Uhr geöffnet\\n\\nNachtschwimmen :\\nWird spontan bei gutem Wetter statt finden.\\n\\n\\nAn der Solennität 27.06.16 bleibt das Freibad den ganzen Tag geschlossen.\\n\\n\\nBei schlechtem Wetter kann die Schliesszeit vorverlegt werden oder\\ndie Anlage kann den ganzen Tag geschlossen bleiben.\",\"preise\": \"Erwachsene :             SFr. 5.- / Saison: Fr. 65.-\\nStudenten / AHV / IV :   SFr. 4.- / Saison: Fr. 45.-\\nSchüler :                SFr. 2.- / Saison: Fr. 25.-\\nChipkarte:               SFr. 5.-  (Verkauf)\",\"info\": \"\",\"wetterort\": \"Bern\",\"uv_station_name\": \"Bern\",\"uv_wert\": 4,\"uv_date\": \"2019-05-27 00:00:00\",\"uv_date_pretty\": \"27.05.\",\"becken\": {\"Schwimmbecken\": {\"beckenid\": 1,\"beckenname\": \"Schwimmbecken\",\"temp\": \"20.5\",\"date\": \"2019-05-25 13:44:03\",\"typ\": \"Freibad\",\"status\": \"geöffnet\",\"smskeywords\": \";BURGDORF;\",\"smsname\": \"Burgdorf Freibad\",\"ismain\": \"T   \",\"date_pretty\": \"25.05.\"},\"Hallenbad\": {\"beckenid\": 127,\"beckenname\": \"Hallenbad\",\"temp\": \"27.0\",\"date\": \"2019-05-25 13:44:03\",\"typ\": \"Hallenbad\",\"status\": \"geöffnet\",\"smskeywords\": null,\"smsname\": null,\"ismain\": \"T   \",\"date_pretty\": \"25.05.\"}},\"bilder\": [{\"image\": \"img/baeder/1/1.jpg\",\"text\": \"\"},{\"image\": \"img/baeder/1/2.jpg\",\"text\": \"Beschreibung\\n\"},{\"image\": \"img/baeder/1/3.jpg\",\"text\": \"\"},{\"image\": \"img/baeder/1/4.jpg\",\"text\": \"\"},{\"image\": \"img/baeder/1/5.jpg\",\"text\": \"\"}],\"wetter\": [{\"wetter_symbol\": 10,\"wetter_temp\": \"20.0\",\"wetter_date\": \"2019-05-27 00:00:00\",\"wetter_date_pretty\": \"27.05.\"},{\"wetter_symbol\": 5,\"wetter_temp\": \"19.0\",\"wetter_date\": \"2019-05-26 00:00:00\",\"wetter_date_pretty\": \"26.05.\"}]}";


    @Test
    public void createBadiFromJsonString() throws JSONException {
        Badi badi = WieWarmJsonParser.createBadiFromJsonString(jsonBadi);
        Assert.assertTrue(badi.equals(createTestBadi()));
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

        becken.setTemperature(27.0);
        becken.setName("Hallenbad");

        return badi;
    }



}