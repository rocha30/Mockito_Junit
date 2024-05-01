package org.example;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class NotificadorEmailTest {

    @Mock
    private EmailCliente emailClienteMock;

    @Test
    public void testNotificar() {
        NotificadorEmail notificador = new NotificadorEmail(emailClienteMock);
        notificador.notificar("ejemplo@test.com", "Hola Mundo");

        // Verificar que emailClienteMock.enviarCorreo se llamó con los argumentos correctos
        verify(emailClienteMock).enviarCorreo("ejemplo@test.com", "Hola Mundo");
    }

    // Test para verificar que no se envía correo con dirección vacía
    @Test
    public void testNotificarConDireccionVacia() {
        NotificadorEmail notificador = new NotificadorEmail(emailClienteMock);
        notificador.notificar("", "Mensaje");

        // Verificar que no se realiza el envío si la dirección es vacía
        verify(emailClienteMock, times(0)).enviarCorreo(anyString(), anyString());
    }

    // Test para verificar el comportamiento con mensaje nulo
    @Test
    public void testNotificarConMensajeNulo() {
        NotificadorEmail notificador = new NotificadorEmail(emailClienteMock);
        notificador.notificar("ejemplo@test.com", null);

        // Verificar que se maneja adecuadamente un mensaje nulo
        // Esto puede variar según la implementación deseada
        // TODO: Implementa la lógica de verificación según tu lógica de negocio
    }

    @Test

    public void testNotificartFalloEnvioCorreo(){
        doThrow(new RuntimeException("Error al enviar correo ")).when(emailClienteMock).enviarCorreo(anyString(), anyString());

        NotificadorEmail notificador = new NotificadorEmail(emailClienteMock);
        notificador.notificar("ejemplo@test.com", "El correo no fue enviado correctamente");

    }

    @Test
    public void testNotificarCorreoLargo(){
        String correoLargo = "Esto es un ejemplo de un correo largo que tiene muchos caracteres, ya no se que más puedo escribir para que siga siendo más largo, bueno espero que hayas leido todo ";

        NotificadorEmail notificador =  new NotificadorEmail(emailClienteMock);
        notificador.notificar("ejemplo@test.com ", correoLargo);
    }

}
