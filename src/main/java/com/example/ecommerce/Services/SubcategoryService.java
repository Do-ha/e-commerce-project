package com.example.ecommerce.Services;

import com.example.ecommerce.DTOs.CategoryDto;
import com.example.ecommerce.DTOs.SubcategoryDto;
import com.example.ecommerce.Entities.Category;
import com.example.ecommerce.Entities.Subcategory;
import com.example.ecommerce.Repositories.SubCategoryRepository;
import com.example.ecommerce.mappers.CategoryMapper;
import com.example.ecommerce.mappers.SubcategoryMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubcategoryService{
private SubCategoryRepository subCategoryRepository;

  public SubcategoryDto addsubcategory(SubcategoryDto subcategoryDto){
      Subcategory subcategory = subCategoryRepository.save(SubcategoryMapper.mapDtoToEntity(subcategoryDto));

      System.out.println(subcategory);

      return SubcategoryMapper.mapEntityToDto(subcategory);
  }


  public SubcategoryDto updateSubcategory(SubcategoryDto updatedSubcategoryDto, Long id) {
    Optional<Subcategory> subcategoryToUpdateOptional = this.subCategoryRepository.findById(id);

    if (!subcategoryToUpdateOptional.isPresent()) {
      // Product not found, handle accordingly (return null or throw an exception)
      return null;
    }

    Subcategory subcategoryToUpdate = subcategoryToUpdateOptional.get();

    // Update attributes based on the provided ProductDto
    if (updatedSubcategoryDto.subCategoryName() != null) {
      subcategoryToUpdate.setSubCategoryName(updatedSubcategoryDto.subCategoryName());
    }
//    if (updatedCategoryDto.getSubCategories() != null) {
//      // Correct the update for subCategories
//      categoryToUpdate.setSubCategories(updatedCategoryDto.getSubCategories());
//    }

    Subcategory updatedSubcategory = this.subCategoryRepository.save(subcategoryToUpdate);

    // Map the updated product to a ProductDto and return it
    return SubcategoryMapper.mapEntityToDto(updatedSubcategory);
  }

    public List<SubcategoryDto> getAllSubcategories(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "limit", defaultValue = "10") int limit)

    {


            // Use PageRequest for pagination
            Pageable pageable = PageRequest.of(page, limit);

            // Use findAll with pageable directly
            Page<Subcategory> subcategoryPage = subCategoryRepository.findAll(pageable);
            if(subcategoryPage.hasContent()){
                return subcategoryPage.getContent().stream()
                        .map(SubcategoryMapper::mapEntityToDto)
                        .collect(Collectors.toList());
            }
            else{ throw new EntityNotFoundException("Subcategory not found");
            }
        }
    public SubcategoryDto getSubcategoryById(Long id) {
        Optional<Subcategory> subcategoryOptional = subCategoryRepository.findById(id);

        if (subcategoryOptional.isPresent()) {
            SubcategoryDto subcategoryDto = SubcategoryMapper.mapEntityToDto(subcategoryOptional.get());
            return subcategoryDto ;
        }
        else {
            throw new EntityNotFoundException("Subcategory not found");
        }}

    public List<SubcategoryDto> searchSubcategories(int page,int limit,String query) {

        // Use PageRequest for pagination
        Pageable pageable = PageRequest.of(page , limit); // Adjust page to be zero-indexed

        // Use findAll or findByProductName with pageable directly
        Page<Subcategory> subcategoryPage;
        if (query != null || !query.isEmpty()) {
            System.out.println(query);
            subcategoryPage = subCategoryRepository.findBySubCategoriesName(query, pageable);
        } else {
            System.out.println("non");
            subcategoryPage = subCategoryRepository.findAll(pageable);
        }

        return subcategoryPage.getContent().stream()
                .map(SubcategoryMapper::mapEntityToDto)
                .collect(Collectors.toList());

    }
    public SubcategoryDto DeleteSubcategory(Long id) {
        Optional<Subcategory> subcategoryTodeleteOptional = this.subCategoryRepository.findById(id);


        if (subcategoryTodeleteOptional.isEmpty()) {
            throw new EntityNotFoundException("Subcategory with id " + id +" not found");
        }
        Subcategory subcategoryTodelete = subcategoryTodeleteOptional.get();
        if (subcategoryTodelete.getProduct().isEmpty()) {
            this.subCategoryRepository.delete(subcategoryTodelete);
            return SubcategoryMapper.mapEntityToDto(subcategoryTodelete);
        }
        else {
            throw new EntityNotFoundException("Subcategory with id " + id + " has attached product and cannot be deleted.");
        }
}

}
