package ru.haazad.java.architectures.adapters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.haazad.java.architectures.dtos.CommandDto;
import ru.haazad.java.architectures.dtos.ProductDto;
import ru.haazad.java.architectures.enums.CommandAction;
import ru.haazad.java.architectures.services.ProductService;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductAdapter {

    private static final String QUEUE = "product_queue";

    private final ObjectMapper mapper;
    private final ProductService productService;

    @RabbitListener(queues = QUEUE)
    public void listen(String input) throws JsonProcessingException {
        log.debug("Input command: " + input);
        CommandDto commandDto = mapper.readValue(input, CommandDto.class);
        execute(commandDto.getAction(), mapper.readValue(mapper.writeValueAsString(commandDto.getEntity()), ProductDto.class));
    }

    private void execute(CommandAction action, ProductDto productDto) {
        switch (action){
            case CREATE:
            case UPDATE:
                productService.saveProduct(productDto);
                break;
            case DELETE:
                productService.deleteProduct(productDto.getId());
                break;
        }
    }
}
