import axios from "axios";

export default class ProductService {
  getProducts() {
    return axios.get("http://localhost:8888/v1/product/getAll");
  }
}
