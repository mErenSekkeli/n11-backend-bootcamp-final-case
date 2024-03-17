import axios from 'axios';

const COMPANY_API_BASE_URL = 'http://localhost:8081/api/v1/companies';

export const saveCustomer = (customerData) => {
        const response = axios.post('/api/v1/customer', customerData);
        return response;
};

export const getAllCustomers = () => {
    const response = axios.get('/api/v1/customer');
    return response;
}

export const recommendCompanies = (customerId) => {
    const response = axios.get('/api/v1/recommendations/user/' + customerId);
    return response;
}

export const saveCompany = (companyData) => {
    const response = axios.post(COMPANY_API_BASE_URL, companyData);
    return response;
}

export const getAllCompanies = () => {
    const response = axios.get(COMPANY_API_BASE_URL);
    return response;
}

export const deleteCompany = (companyId) => {
    const response = axios.delete(COMPANY_API_BASE_URL + '/' + companyId);
    return response;
}