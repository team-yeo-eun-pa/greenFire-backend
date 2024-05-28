package yep.greenFire.greenfirebackend.admin.notice.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.admin.notice.domain.entity.Category;
import yep.greenFire.greenfirebackend.admin.notice.dto.request.CategoryCreateRequest;
import yep.greenFire.greenfirebackend.admin.notice.dto.request.CategoryUpdateRequest;
import yep.greenFire.greenfirebackend.admin.notice.service.CategoryService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category-management")
    public ResponseEntity<List<Category>> getAdminCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);

    }

    @PostMapping("/categories")
    public ResponseEntity<Void> save(
            @RequestBody @Valid final CategoryCreateRequest categoryCreateRequest
            ) {
        categoryService.save(categoryCreateRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/categories/{categoryCode}")
    public ResponseEntity<Void> modify(
            @PathVariable final Long categoryCode,
            @RequestBody @Valid final CategoryUpdateRequest categoryUpdateRequest
    ) {
        categoryService.modify(categoryCode, categoryUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/categories/{categoryCode}")
    public ResponseEntity<Void> remove(@PathVariable final Long categoryCode) {
        categoryService.remove(categoryCode);

        return ResponseEntity.noContent().build();
    }
}
