package com.alexiae.kafka.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {


    @Bean
    public NewTopic generateTopic() {
        Map<String, String> configurations = new HashMap<>();

        // ACCIÓN PARA LOS MENSAJES: EN ESTE CASO, ELIMINAR Y DEJAR EL MÁS ACTUAL
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
        // RETENER LOS MENSAJES POR 1 DÍA (VALOR EN MILISEGUNDOS); DEFAULT -1 (NO SE BORRA)
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000"); // 1 día = 86400000 ms

        // TAMAÑO MÁXIMO DEL SEGMENTO: 1 GB. SI LLEGA A ESTA CANTIDAD, SE CREA UN NUEVO SEGMENTO
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824"); // 1 GB = 1073741824 bytes

        // TAMAÑO MÁXIMO DE CADA MENSAJE: DEFAULT 1 MB
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1048576"); // 1 MB = 1048576 bytes

        return TopicBuilder.name("producerDev-Topic")
                .partitions(2)
                .replicas(1)
                .configs(configurations)
                .build();
    }
}
