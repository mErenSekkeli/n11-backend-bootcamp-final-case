import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap';

function CustomerForm({ onSave }) {
    const [customer, setCustomer] = useState({
        name: '',
        surname: '',
        birthDate: '',
        username: '',
        phoneNumber: '',
        email: '',
        status: 'ACTIVE', // Varsayılan bir değer
        latitude: '',
        longitude: ''
    });

    const handleChange = (event) => {
        const { name, value } = event.target;
        setCustomer({ ...customer, [name]: value });
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        onSave(customer);
    };

    return (
        <Form onSubmit={handleSubmit}>
            <Form.Group className="mb-3">
                <Form.Label>Name</Form.Label>
                <Form.Control type="text" name="name" value={customer.name} onChange={handleChange} placeholder="Name" required />
            </Form.Group>

            <Form.Group className="mb-3">
                <Form.Label>Surname</Form.Label>
                <Form.Control type="text" name="surname" value={customer.surname} onChange={handleChange} placeholder="Surname" required />
            </Form.Group>

            <Form.Group className="mb-3">
                <Form.Label>Birth Date</Form.Label>
                <Form.Control type="date" name="birthDate" value={customer.birthDate} onChange={handleChange} placeholder="Birth Date" />
            </Form.Group>

            <Form.Group className="mb-3">
                <Form.Label>Username</Form.Label>
                <Form.Control type="text" name="username" value={customer.username} onChange={handleChange} placeholder="Username" required />
            </Form.Group>

            <Form.Group className="mb-3">
                <Form.Label>Phone Number</Form.Label>
                <Form.Control type="tel" name="phoneNumber" value={customer.phoneNumber} onChange={handleChange} placeholder="+905555555555" required pattern="^\+(?:[0-9]●?){6,14}[0-9]$" />
            </Form.Group>

            <Form.Group className="mb-3">
                <Form.Label>Email</Form.Label>
                <Form.Control type="email" name="email" value={customer.email} onChange={handleChange} placeholder="Email" required />
            </Form.Group>

            <Form.Group className="mb-3">
                <Form.Label>Status</Form.Label>
                <Form.Select name="status" value={customer.status} onChange={handleChange} required>
                    <option value="ACTIVE">Active</option>
                    <option value="PASSIVE">Passive</option>
                </Form.Select>
            </Form.Group>

            <Form.Group className="mb-3">
                <Form.Label>Latitude</Form.Label>
                <Form.Control type="number" name="latitude" value={customer.latitude} onChange={handleChange} placeholder="Latitude" required />
            </Form.Group>

            <Form.Group className="mb-3">
                <Form.Label>Longitude</Form.Label>
                <Form.Control type="number" name="longitude" value={customer.longitude} onChange={handleChange} placeholder="Longitude" required />
            </Form.Group>

            <Button variant="primary" type="submit">
                Save Customer
            </Button>
        </Form>
    );
}

export default CustomerForm;
