package org.example;

public class NotificadorEmail {
    private EmailCliente emailCliente;

    public NotificadorEmail(EmailCliente emailCliente) {
        this.emailCliente = emailCliente;
    }

    public void notificar(String direccion, String mensaje) {
        // Lógica para enviar una notificación por correo
        emailCliente.enviarCorreo(direccion, mensaje);
    }
}
