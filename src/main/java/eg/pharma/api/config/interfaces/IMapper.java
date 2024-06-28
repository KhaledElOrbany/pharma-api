package eg.pharma.api.config.interfaces;

import java.util.List;

public interface IMapper<E, R, Q> {
    List<R> toResourceList(List<E> entities);
    R toResource(E entity);
    E toEntity(Q request);
    E updateEntity(E entity, Q request);
}

