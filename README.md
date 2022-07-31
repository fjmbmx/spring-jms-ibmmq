# Integracion de Spring con IBM MQ

Conexión a IBM MQ con Spring Boot y JMS Spring Boot se integra casi a la perfección con IBM MQ, gracias a su compatibilidad con JMS.Puede usar Spring Initializr para generar el código auxiliar del proyecto para una aplicación simple con JMS. 

Los beans de fábrica JMS deben configurarse para establecer la conexión. 
javax.jms.ConnectionFactory es una fábrica genérica utilizada tanto por productores como por consumidores de mensajes. org.springframework.jms.config.DefaultJmsListenerContainerFactory es responsable de la configuración del consumidor de mensajes, org.springframework.jms.core.JmsTemplate se puede usar para productores de mensajes simples. El Session Acknowledge Mode se establece en Session.CLIENT_ACKNOWLEDGE, lo que significa que la entrega del mensaje se reconoce como parte de la transacción. Si se revierte la transacción, se volverá a enviar el mensaje JMS. Vale la pena señalar que Connection Factory tiene el tipo de transporte establecido en WMQConstants.WMQ_CM_CLIENT.


MQProperties.java para llenar la fábrica de conexiones. ConnectionConfiguration.java es el único bean que está estrechamente relacionado con la implementación de IBM MQ. Para transacciones distribuidas (XA), MQXAConnectionFactory es el enfoque adecuado. Si no confía en las transacciones distribuidas, no dude en utilizar MQConnectionFactory en su lugar. Este proyecto utiliza MQ ConnectionFactory.

La configuración de beans específicos para la aplicación actual se incluye dentro de una clase separada para facilitar el reemplazo de la fábrica de conexiones para las pruebas. Casi todos los beans de la clase a continuación dependen de la fábrica de conexiones definida previamente. MQConfiguration.java utilizado para configurar los beans para esta aplicación.

Habiendo definido todos los beans, ahora es el momento de usarlos en un servicio concreto, que sirve como consumidor y productor de mensajes, que es MQGateway.java

## spring-jms-ibmmq

Requerimienos:
Contar con los daos de conexion a IBMMQ

#Compilacion completa
mvn clean install 

#Compilacion ignoranto test
#mvn clean install -Dmaven.test.skip=true

#Para correr la aplicacion desde el cmd
mvn spring-boot:run

## Para usar el puerto 8080 aleatorio en lugar del predeterminado para la aplicación Spring Boot, use la siguiente propiedad en 'application.properties'
servidor.puerto=0


#SwaggerLink
http://localhost:8080/swagger-ui.html#!/web-controller

Nota: el puerto: 8080 puede diferir, si desea usar un puerto aleatorio para ejecutar su aplicación Spring-Boot.
