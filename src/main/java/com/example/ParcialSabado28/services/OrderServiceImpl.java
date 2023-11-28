package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.CreateOrderRequestDto;
import com.example.ParcialSabado28.controller.OrderDto;
import com.example.ParcialSabado28.model.*;
import com.example.ParcialSabado28.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderServiceImpl implements IOrderService {

    OrderRepository orderRepository;
    OrderDtoMapper orderDtoMapper;
    OrderMapper orderMapper;
    IdentifierRepository identifierRepository;
    ProductRepository productRepository;
    OrderDetailRepository orderDetailRepository;
    CustomerRepository customerRepository;
    EmployeeRepository employeeRepository;
    ShipperRepository shipperRepository;


    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders
                .stream()
                .map(orderDtoMapper)
                .toList();
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = Optional.of(orderDto).map(orderMapper).orElseThrow();

        // Obtenemos un nuevo identificador para la tabla Orders.
        int orderId = identifierRepository.nextValue(Order.TABLE_NAME);

        // Asignamos el nuevo identificador a Order.
        order.setOrderId(orderId);

        Order savedOrder = orderRepository.save(order);

        return orderDtoMapper.apply(savedOrder);
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto) {
        Order order = Optional.of(orderDto).map(orderMapper).orElseThrow();
        orderRepository.save(order);
        return orderDtoMapper.apply(order);
    }

    @Override
    public OrderDto deleteOrderById(Integer orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        OrderDto deleted = optionalOrder.map(orderDtoMapper).orElseThrow();
        optionalOrder.ifPresent(orderRepository::delete);
        return deleted;
    }

    @Override
    public OrderDto findOrderById(Integer orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        return optionalOrder.map(orderDtoMapper).orElseThrow();
    }

    @Override
    public OrderDto createCustomOrder(CreateOrderRequestDto dto) {
        // Obtener el cliente, empleado y transportista basados en los IDs proporcionados por parametro
        Customer customer = customerRepository.findById(dto.getCustomerId()).orElseThrow();
        Employee employee = employeeRepository.findById(dto.getEmployeeId()).orElseThrow();
        Shipper shipper = shipperRepository.findById(dto.getShipperId()).orElseThrow();
      /*Mas adelante este customer, employye y shipper se setearan como properties de la nueva order a crear,
      sumado a que servira para settear otras properties de order como : ship name, ship address, etc
       */
        /*La nueva order a crear debe tener solamente productos  que tengan los siguientes filtros:
        1.  su proveedor debe haber sido pasado por parametro
        2. Su categoria debe haber sido pasado por parametro
        3.El stock futuro (UnitsInStock + UnitsOnOrder) sea menor que stockRequerido que llega como parámetro
         */
        List<OrderDetail> orderDetails = productRepository.findAll()
                .stream()
                .filter(product -> product.getSupplier().getSupplierId().equals(dto.getSupplierId()) &&
                        product.getCategory().getCategoryId().equals(dto.getCategoryId()) &&
                        (product.getUnitsInStock() + product.getUnitsOnOrder()) < dto.getStockRequired())
                .map(product -> {
                    /*transformo cada producto filtrado en una order detail
                    habiendo obtenido previamente antes el quantityToOrder y el discount.
                     */
                    int quantityToOrder = dto.getStockRequired() - (product.getUnitsInStock() + product.getUnitsOnOrder());
                    double discount = quantityToOrder < 100 ? 0.0 : 0.10;
                    return new OrderDetail(new OrderDetailId(), null, product, product.getUnitPrice(), quantityToOrder, discount);
                })
                .collect(Collectors.toList());

        // Crear y configurar la orden
        Order order = new Order();
        order.setOrderId(identifierRepository.nextValue(Order.TABLE_NAME));
        order.setCustomer(customer);
        order.setEmployee(employee);
        order.setShipper(shipper);
        order.setFreight(0.0);//el número de transporte (Freight) será 0 por que aún no está asignado
        order.setOrderDetails(orderDetails);
        order.setShipName(customer.getCompanyName()); //los datos de Ship son la copia de la dirección del cliente.
        order.setShipAddress(customer.getAddress());  //los datos de Ship son la copia de la dirección del cliente.
        order.setShipCity(customer.getCity());  //los datos de Ship son la copia de la dirección del cliente.
        order.setShipRegion(customer.getRegion()); //los datos de Ship son la copia de la dirección del cliente.
        order.setShipPostalCode(customer.getPostalCode());//los datos de Ship son la copia de la dirección del cliente.
        order.setShipCountry(customer.getCountry());//los datos de Ship son la copia de la dirección del cliente.

        // Guardar el pedido y sus detalles
        order = orderRepository.save(order);
        final Integer orderId = order.getOrderId();
        int totalQuantity = 0;
        for (OrderDetail detail : orderDetails) {
            detail.setId(new OrderDetailId(orderId, detail.getProduct().getProductId()));
            orderDetailRepository.save(detail);
            totalQuantity += detail.getQuantity();
        }

        // Convertir a OrderDto
        OrderDto orderDto = orderDtoMapper.apply(order);
        orderDto.setTotalQuantity(totalQuantity);
        return orderDto;
    }

}