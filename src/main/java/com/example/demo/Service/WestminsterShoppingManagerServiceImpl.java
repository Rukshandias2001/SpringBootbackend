package com.example.demo.Service;

import com.example.demo.Config.CloudinaryConfig;
import com.example.demo.Entities.*;
import com.example.demo.Repositories.ProductRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.persistence.EntityManager;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Qualifier
@Service
public class WestminsterShoppingManagerServiceImpl implements ShoppingManagerService {

    private CloudinaryConfig cloudinaryConfig;
    @Autowired
    private EntityManager entityManager;

    @Autowired
    public void setCloudinary(CloudinaryConfig thecloudinaryConfig) {
        this.cloudinaryConfig = thecloudinaryConfig;


    }

    @Autowired
    ProductRepository productRepository;

    @Override
    public ResponseEntity<Product> deleteProducts(int productId) {

           Product p1 = productRepository.findById(productId).get();
           productRepository.deleteById(productId);
           return ResponseEntity.ok().body(p1);


    }

    @Override
    public ResponseEntity<Object> addProduct(Product product, MultipartFile file) {
            if(!file.isEmpty()){
                String url = imageUploader((file));
                product.setImageUrl(url);
                Product getProduct = productRepository.save(product);
                return  ResponseEntity.ok().body(getProduct);
            }else{
                return  ResponseEntity.ok().body("Image cannot be empty ! ");
            }


    }

    @Override
    public ResponseEntity<String> updateProduct(Product product,int id) {

//        Boolean updated = productRepository;
//        if(updated){
//            return  ResponseEntity.ok().body("Successfully saved the data");
//        }else{
//            return  ResponseEntity.ok().body("Something went wrong");
//        }
        return  ResponseEntity.ok().body("Successfully saved the data");
    }

    @Override
    public ResponseEntity<Product> getProducts(int productId) {
        Product byId = productRepository.findById(productId).get();
        return ResponseEntity.ok().body(byId);

    }

    @Override
    public Response getProductsByCategory(int categoryId) {
        return null;
    }

    @Override
    public ResponseEntity<List<Product>> getElectronics() {
        List<Product> allElectronics = productRepository.getAllElectronics();
            return ResponseEntity.ok().body(allElectronics);


    }

    @Override
    public ResponseEntity<List<Product>> getClothings() {
        List <Product> listOfClothes = productRepository.getAllClothings();
        return ResponseEntity.ok().body(listOfClothes);


    }


    @Override
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> allProducts = productRepository.findAll();

            return  ResponseEntity.ok().body(allProducts);


    }

    @Override
    public ResponseEntity<Product> updateProductElectronicsProduct(Electronics electronics, int id, MultipartFile file) {

        Optional<Product> getproduct  = productRepository.findById(id);
            Electronics updateElectronicProduct = (Electronics) getproduct.get();
            updateElectronicProduct.setProductName(electronics.getProductName());
            updateElectronicProduct.setWarrenty(electronics.getWarrenty());
            updateElectronicProduct.setDescription(electronics.getDescription());

            updateElectronicProduct.setBrand(electronics.getBrand());
            updateElectronicProduct.setPrice(electronics.getPrice());
            updateElectronicProduct.setQuantity(electronics.getQuantity());
            updateElectronicProduct.setCategoryId(electronics.getCategoryId());
            updateElectronicProduct.setType(electronics.getType());
            if(!file.isEmpty()){
                String url = imageUploader((file));
                updateElectronicProduct.setImageUrl(url);
            }
            Electronics updated = productRepository.save(updateElectronicProduct);
            return ResponseEntity.ok().body(updated);

    }

    @Override
    public ResponseEntity<Product> updateProductClothings(Clothings clothings, int id, MultipartFile file) {
        Optional<Product> getproduct  = productRepository.findById(id);
        Clothings updateClothingProduct = (Clothings) getproduct.get();
        updateClothingProduct.setProductName(clothings.getProductName());
        updateClothingProduct.setColour(clothings.getColour());
        updateClothingProduct.setPrice(clothings.getPrice());
        updateClothingProduct.setQuantity(clothings.getQuantity());
        updateClothingProduct.setCategoryId(clothings.getCategoryId());
        updateClothingProduct.setType(clothings.getType());
        updateClothingProduct.setDescription(clothings.getDescription());
        updateClothingProduct.setSize(clothings.getSize());
        if(file!=null){
            if(!file.isEmpty()){
                String url = imageUploader((file));
                updateClothingProduct.setImageUrl(url);
            }
        }

        Clothings updated = productRepository.save(updateClothingProduct);
        return ResponseEntity.ok().body(updated);
    }


    private String imageUploader(MultipartFile file){
        try {
            Cloudinary cloudinary =  cloudinaryConfig.cloudinary();
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return uploadResult.get("url").toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<List<Country>> listOfCountries(){
        String query= "SELECT c FROM Country  c";
        List<Country> resultList = entityManager.createQuery(query, Country.class).getResultList();
        return ResponseEntity.ok().body(resultList);
    }

    public ResponseEntity<List<State>> listOfStates(int id){
        String query= "SELECT c FROM State c where c.country.id="+id;
        List<State> resultList = entityManager.createQuery(query, State.class).getResultList();
        return ResponseEntity.ok().body(resultList);
    }

    public ResponseEntity<List<User>> registerUser(int id){

        return null;
    }




}
