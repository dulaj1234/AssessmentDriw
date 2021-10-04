import axios from "axios";

export const GetList = () => {
    return axios.get('http://localhost:9091/itemlist')
}

export const AddProduct = (items) => {
    return axios.post('http://localhost:9091/calculate', items)
}