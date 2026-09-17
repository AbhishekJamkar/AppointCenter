package com.zosh.service.impl;

import com.zosh.modal.ServiceOffering;
import com.zosh.payload.dto.CategoryDTO;
import com.zosh.payload.dto.BusinessDTO;
import com.zosh.payload.dto.ServiceDTO;
import com.zosh.repository.ServiceOfferingRepository;
import com.zosh.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceOfferingImpl implements ServiceOfferingService {


    private final ServiceOfferingRepository serviceOfferingRepository;

    @Override
    public ServiceOffering createService(ServiceDTO service,
                                         BusinessDTO business,
                                         CategoryDTO category) {
        ServiceOffering serviceOffering=new ServiceOffering();
        serviceOffering.setName(service.getName());
        serviceOffering.setDescription(service.getDescription());
        serviceOffering.setPrice(service.getPrice());
        serviceOffering.setDuration(service.getDuration());
        serviceOffering.setImage(service.getImage());
        serviceOffering.setBusinessId(business.getId());
        serviceOffering.setCategoryId(category.getId());
        return serviceOfferingRepository.save(serviceOffering);
    }

    @Override
    public ServiceOffering updateService(Long serviceId, ServiceOffering service) throws Exception {
        Optional<ServiceOffering> existingService = serviceOfferingRepository.findById(serviceId);
        if (existingService.isPresent()) {
            ServiceOffering updatedService = existingService.get();
            updatedService.setName(service.getName());
            updatedService.setDescription(service.getDescription());
            updatedService.setPrice(service.getPrice());
            updatedService.setDuration(service.getDuration());
            if(service.getImage()!=null){
                updatedService.setImage(service.getImage());
            }

            return serviceOfferingRepository.save(updatedService);
        } else {
            throw new Exception("Service not found");
        }
    }

    @Override
    public Set<ServiceOffering> getAllServicesByBusinessId(Long businessId,
                                                        Long categoryId) {
        Set<ServiceOffering> services = serviceOfferingRepository.findByBusinessId(businessId);
        if(categoryId != null) {
            services=services.stream()
                    .filter(service -> service.getCategoryId() != null && service.getCategoryId().equals(categoryId))
                    .collect(Collectors.toSet());
        }

        return services;
    }

    @Override
    public ServiceOffering getServiceById(Long serviceId) {
        Optional<ServiceOffering> service = serviceOfferingRepository
                .findById(serviceId);
        return service.orElse(null);
    }


    @Override
    public Set<ServiceOffering> getServicesByIds(Set<Long> ids) {
        List<ServiceOffering> services = serviceOfferingRepository
                .findAllById(ids);
        return new HashSet<>(services);
    }
}
