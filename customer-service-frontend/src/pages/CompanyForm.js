import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap';

function CompanyForm({ onSave }) {
    const [company, setCompany] = useState({
        name: '',
        address: '',
        phone: '',
        email: '',
        website: '',
        latitude: 0,
        longitude: 0,
        status: 'ACTIVE' // Varsayılan bir değer
    });

    const handleChange = (event) => {
        const { name, value } = event.target;
        setCompany({ ...company, [name]: value });
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        onSave(company);
    };

    return (
        <Form onSubmit={handleSubmit}>
            <Form.Group className="mb-3">
                <Form.Label>Name</Form.Label>
                <Form.Control
                    type="text"
                    name="name"
                    value={company.name}
                    onChange={handleChange}
                    placeholder="Name"
                    required
                />
            </Form.Group>

            <Form.Group className="mb-3">
                <Form.Label>Address</Form.Label>
                <Form.Control
                    type="text"
                    name="address"
                    value={company.address}
                    onChange={handleChange}
                    placeholder="Address"
                    required
                />
            </Form.Group>

            <Form.Group className="mb-3">
                <Form.Label>Phone Number</Form.Label>
                <Form.Control
                    type="tel"
                    name="phone"
                    value={company.phone}
                    onChange={handleChange}
                    placeholder="+905555555555"
                    pattern="^\+(?:[0-9]●?){6,14}[0-9]$"
                />
            </Form.Group>

            <Form.Group className="mb-3">
                <Form.Label>Email</Form.Label>
                <Form.Control
                    type="email"
                    name="email"
                    value={company.email}
                    onChange={handleChange}
                    placeholder="Email"
                />
            </Form.Group>

            <Form.Group className="mb-3">
                <Form.Label>Website</Form.Label>
                <Form.Control
                    type="text"
                    name="website"
                    value={company.website}
                    onChange={handleChange}
                    placeholder="Website"
                />
            </Form.Group>

            <Form.Group className="mb-3">
                <Form.Label>Latitude</Form.Label>
                <Form.Control
                    type="number"
                    name="latitude"
                    value={company.latitude}
                    onChange={handleChange}
                    placeholder="Latitude"
                    required
                />
            </Form.Group>

            <Form.Group className="mb-3">
                <Form.Label>Longitude</Form.Label>
                <Form.Control
                    type="number"
                    name="longitude"
                    value={company.longitude}
                    onChange={handleChange}
                    placeholder="Longitude"
                    required
                />
            </Form.Group>

            <Form.Group className="mb-3">
                <Form.Label>Status</Form.Label>
                <Form.Select name="status" value={company.status} onChange={handleChange} required>
                    <option value="ACTIVE">Active</option>
                    <option value="PASSIVE">Passive</option>
                </Form.Select>
            </Form.Group>

            <Button variant="primary" type="submit">
                Save Company
            </Button>
        </Form>
    );
}

export default CompanyForm;
