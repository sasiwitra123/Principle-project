package com.example.costumerentalsystem.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.example.costumerentalsystem.domain.entity.Category;
import com.example.costumerentalsystem.repository.CategoryRepository;

/**
 * ชั่วคราว: ฟอร์มเพิ่ม/แก้ไขชุดเดิมส่งชื่อหมวดหมู่เป็นข้อความ (เช่น "ชุดไทย")
 * ตัวแปลงนี้แปลงเป็น Category Entity ตอน bind ฟอร์ม เพื่อให้หน้า admin เดิมใช้ได้
 * จะลบเมื่อ Part C เปลี่ยนเป็น CostumeRequest DTO (categoryId)
 */
@Component
public class CategoryConverter implements Converter<String, Category> {

    private final CategoryRepository categoryRepository;

    public CategoryConverter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category convert(@NonNull String source) {
        String name = source.trim();
        if (name.isEmpty()) {
            return null;
        }
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("ไม่พบหมวดหมู่: " + name));
    }
}
