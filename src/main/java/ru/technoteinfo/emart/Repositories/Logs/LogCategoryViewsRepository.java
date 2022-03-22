package ru.technoteinfo.emart.Repositories.Logs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.technoteinfo.emart.Entity.Logs.LogCategoryViews;

@Repository
public interface LogCategoryViewsRepository extends JpaRepository<LogCategoryViews, Long> {
}
