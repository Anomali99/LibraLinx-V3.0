/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.ByteArrayOutputStream;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import servis.ServiceEmail;

/**
 *
 * @author fatiq
 */
public class DaoEmail implements ServiceEmail {

    private String GMail = "fatiqnur@gmail.com";
    private String pass = "ubwl qrza mcou bihj";
    private Properties pros = new Properties();
    private Session ses = null;

    public DaoEmail() {
        pros.put("mail.smtp.auth", "true");
        pros.put("mail.smtp.starttls.enable", "true");
        pros.put("mail.smtp.host", "smtp.gmail.com");
        pros.put("mail.smtp.port", "587");

        ses = Session.getInstance(pros, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(GMail, pass);
            }
        });
    }

    @Override
    public void kririm(String email, String subyek, String namaLaporan, JasperPrint print) {
        try {
            Message mess = new MimeMessage(ses);
            mess.setFrom(new InternetAddress(GMail));
            mess.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            mess.setSubject(subyek);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
            exporter.exportReport();

            DataSource source = new ByteArrayDataSource(outputStream.toByteArray(), "application/pdf");
            MimeBodyPart attachment = new MimeBodyPart();
            attachment.setDataHandler(new DataHandler(source));
            attachment.setFileName(namaLaporan + ".pdf");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(attachment);
            mess.setContent(multipart);
            Transport.send(mess);
            JOptionPane.showMessageDialog(null, "Email terkirim");
        } catch (MessagingException ex) {
            ImageIcon icon = new ImageIcon(getClass().getResource("/img/batal1.png"));
            JOptionPane.showMessageDialog(null, "Gagal mengirim email\nPastikan anda telah menginputkan \nEmail dengan benar", "GAGAL MENGIRIM", 0, icon);
        } catch (JRException ex) {
            Logger.getLogger(DaoEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String krirmKode(String email) {
        Random random = new Random();
        StringBuilder kodeVerifikasi = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            int digit = random.nextInt(10); // Angka acak antara 0 hingga 9
            kodeVerifikasi.append(digit);
        }
        String kode = kodeVerifikasi.toString();
        String x = "Kode Verifikasi anda : "+kode;
        try {
            Message mess = new MimeMessage(ses);
            mess.setFrom(new InternetAddress(GMail));
            mess.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            mess.setSubject("Kode Verifikasi");
            mess.setText(x);

            Transport.send(mess);
            JOptionPane.showMessageDialog(null, "Kode verifikasi terkirim");
        } catch (MessagingException ex) {
            Logger.getLogger(DaoEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kode;
    }
}
