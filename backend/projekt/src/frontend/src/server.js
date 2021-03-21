const axios = require('axios');
const instance = axios.create({
  timeout: 1000,
})
export default instance;
