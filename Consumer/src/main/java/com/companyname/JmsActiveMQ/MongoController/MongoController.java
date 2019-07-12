/*
 * package com.companyname.JmsActiveMQ.MongoController;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.CrossOrigin; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.companyname.JmsActiveMQ.MongoService.MongoServiceImpl;
 * 
 * @RestController public class MongoController {
 * 
 * @Autowired MongoServiceImpl mongoservice;
 * 
 * 
 * 
 * @PostMapping(path = "/getFile")
 * 
 * @CrossOrigin(origins = "http://localhost:4200") public ResponseEntity<Object>
 * getFile(@RequestBody String filename) {
 * System.out.println("--------filename  is--------" + filename); return new
 * ResponseEntity<Object>(mongoservice.getFile(filename), HttpStatus.OK);
 * 
 * } }
 */