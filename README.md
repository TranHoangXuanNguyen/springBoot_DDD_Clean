domain:

Chứa các Entity, Value Object, Repository interface (chỉ interface, không phụ thuộc vào DB).

application (usecase):

Chứa các Service/UseCase, gọi domain repository interface, xử lý logic nghiệp vụ.

infrastructure:

Chứa các Repository implementation, DB config, adapter để kết nối với JPA/Hibernate, Kafka, v.v.

presentation:

Chứa Controller (REST API), DTO request/response.