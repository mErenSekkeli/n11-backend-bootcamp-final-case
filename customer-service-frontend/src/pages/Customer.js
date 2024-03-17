import React, { useState, useEffect } from 'react';
import { Button, Modal, Table } from 'react-bootstrap';
import CustomerForm from './CustomerForm';
import 'bootstrap/dist/css/bootstrap.min.css';
import { saveCustomer as apiSaveCustomer, getAllCustomers, recommendCompanies as recommendCompaniesApi } from '../Api/ApiCalls';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function Customer() {
    const [showModal, setShowModal] = useState(false);

    const [showRecommendations, setShowRecommendations] = useState(false);

    const handleOpenModal = () => setShowModal(true);
    const handleCloseModal = () => setShowModal(false);

    const [customers, setCustomers] = useState([]);

    const [recommendations, setRecommendations] = useState([]);

    useEffect(() => {
        const fetchCustomers = async () => {
            try {
                const response = await getAllCustomers();
                setCustomers(response.data.data || []);
            } catch (error) {
                console.error(error);
                toast.error('There was an error while fetching the customers!');
            }
        };
    
        fetchCustomers();
    }, []);

    const saveCustomer = async (customerData) => {
        try {
            await apiSaveCustomer(customerData);
            handleCloseModal(); // Close the modal after saving the customer
            toast.success('Customer saved successfully!'); // Add a success message
        } catch (error) {
            toast.error('There was an error while saving the customer!');
        }
    };

    const recommendCompanies = async (customerId) => {
        try{
            const response = await recommendCompaniesApi(customerId);
            setRecommendations(response.data.data || []);
            setShowRecommendations(true);
        } catch (error) {
            console.error(error);
            toast.error('There was an error while fetching the recommendations!');
        }
    }

    const goCompanies = () => {
        window.location.href = '/companies';
    }

    return (
        <div className="container mt-3">
            <ToastContainer />
            <h2>Customers</h2>
            <Button variant="primary" onClick={handleOpenModal}>
                Add Customer
            </Button>
            <Button className='m-2' variant="success" onClick={() => goCompanies()}>
                Companies
            </Button>


           {showModal &&
            <Modal show={showModal} onHide={handleCloseModal} keyboard={false}>
                <Modal.Header closeButton>
                    <Modal.Title>Add Customer</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <CustomerForm onSave={saveCustomer} />
                </Modal.Body>
            </Modal>
            }

        {showRecommendations &&
            <Modal show={showRecommendations} onHide={() => setShowRecommendations(false)} keyboard={false} size="xl">
                <Modal.Header closeButton>
                    <Modal.Title>Recommendations</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Table striped bordered hover>
                        <thead>
                            <tr>
                                <th>Company Name</th>
                                <th>Address</th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>Website</th>
                                <th>Latitude</th>
                                <th>Longitude</th>
                                <th>Status</th>
                                <th>Average Rate</th>
                            </tr>
                        </thead>
                        <tbody>
                            {recommendations.map((recommendation, index) => (
                                <tr key={index}>
                                    <td>{recommendation.name}</td>
                                    <td>{recommendation.address}</td>
                                    <td>{recommendation.phone}</td>
                                    <td>{recommendation.email}</td>
                                    <td>{recommendation.website}</td>
                                    <td>{recommendation.latitude}</td>
                                    <td>{recommendation.longitude}</td>
                                    <td>{recommendation.status}</td>
                                    <td>{recommendation.averageRate.toFixed(2)}</td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                </Modal.Body>
            </Modal>
        }

            <div className="container mt-3">
            <h2>Customer List</h2>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Birth Date</th>
                        <th>Username</th>
                        <th>Phone Number</th>
                        <th>Email</th>
                        <th>Status</th>
                        <th>Latitude</th>
                        <th>Longitude</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {customers.map((customer, index) => (
                        <tr key={index}>
                            <td>{customer.name}</td>
                            <td>{customer.surname}</td>
                            <td>{customer.birthDate}</td>
                            <td>{customer.username}</td>
                            <td>{customer.phoneNumber}</td>
                            <td>{customer.email}</td>
                            <td>{customer.status}</td>
                            <td>{customer.latitude}</td>
                            <td>{customer.longitude}</td>
                            <td>
                                <Button onClick={() => recommendCompanies(customer.id)} variant="success">Recommend</Button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </Table>
        </div>

        </div>
    );
}

export default Customer;