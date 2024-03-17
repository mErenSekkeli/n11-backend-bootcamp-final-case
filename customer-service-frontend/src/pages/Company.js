import React, { useState, useEffect } from 'react';
import { Button, Modal, Table } from 'react-bootstrap';
import CompanyForm from './CompanyForm';
import 'bootstrap/dist/css/bootstrap.min.css';
import { saveCompany as apiSaveCompany, getAllCompanies,} from '../Api/ApiCalls';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function Customer() {
    const [showModal, setShowModal] = useState(false);

    const handleOpenModal = () => setShowModal(true);
    const handleCloseModal = () => setShowModal(false);

    const [companies, setCompanies] = useState([]);

    useEffect(() => {
        const fetchCustomers = async () => {
            try {
                const response = await getAllCompanies();
                setCompanies(response.data.data || []);
            } catch (error) {
                console.error(error);
                toast.error('There was an error while fetching the companies!');
            }
        };
    
        fetchCustomers();
    }, []);

    const saveCompany = async (customerData) => {
        try {
            await apiSaveCompany(customerData);
            handleCloseModal(); // Close the modal after saving the customer
            toast.success('Company saved successfully!'); // Add a success message
        } catch (error) {
            toast.error('There was an error while saving the company!');
        }
    };

    const goCustomers = () => {
        window.location.href = '/customers';
    }


    return (
        <div className="container mt-3">
            <ToastContainer />
            <h2>Companies</h2>
            <Button variant="primary" onClick={handleOpenModal}>
                Add Company
            </Button>
            <Button className='m-2' variant="success" onClick={() => goCustomers()}>
                Customers
            </Button>


           {showModal &&
            <Modal show={showModal} onHide={handleCloseModal} keyboard={false}>
                <Modal.Header closeButton>
                    <Modal.Title>Add Company</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <CompanyForm onSave={saveCompany} />
                </Modal.Body>
            </Modal>
            }

            <div className="container mt-3">
            <h2>Company List</h2>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Phone</th>
                        <th>Email</th>
                        <th>Website</th>
                        <th>Status</th>
                        <th>Latitude</th>
                        <th>Longitude</th>
                    </tr>
                </thead>
                <tbody>
                    {companies.map((company, index) => (
                        <tr key={index}>
                            <td>{company.name}</td>
                            <td>{company.address}</td>
                            <td>{company.phone}</td>
                            <td>{company.email}</td>
                            <td>{company.website}</td>
                            <td>{company.status}</td>
                            <td>{company.latitude}</td>
                            <td>{company.longitude}</td>
                        </tr>
                    ))}
                </tbody>
            </Table>
        </div>

        </div>
    );
}

export default Customer;