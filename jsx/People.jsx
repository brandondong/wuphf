import React from 'react';
import ReactDOM from 'react-dom';
import MainNavbar, {MainJumbotron} from './MainNavbar.jsx';
import PageHeader from 'react-bootstrap/lib/PageHeader';
import Button from 'react-bootstrap/lib/Button';
import Modal from 'react-bootstrap/lib/Modal';
import ControlLabel from 'react-bootstrap/lib/ControlLabel';
import FormControl from 'react-bootstrap/lib/FormControl';

class People extends React.Component {
	
	constructor() {
		super();
		this.hideModal = this.hideModal.bind(this);
		this.state = {showModal: false};
	}
	
	hideModal() {
		this.setState({showModal: false});
	}
	
	render() {
		return (
			<div>
				<MainNavbar/>
				<MainJumbotron title="People" message="Filler for now"/>
				<div className="container">
					<ContactsSection/>
					<Button bsSize="large" onClick={() => this.setState({showModal: true})}>Add contact</Button>
				</div>
				<ContactModal showModal={this.state.showModal} hide={this.hideModal}/>
			</div>
		);
	}
}

class ContactsSection extends React.Component {
	render() {
		return (
			<PageHeader>Contacts added</PageHeader>
		);
	}
}

class ContactModal extends React.Component {
	render() {
		return (
			<Modal show={this.props.showModal} onHide={this.props.hide}>
				<Modal.Header closeButton>
					<Modal.Title>Add contact</Modal.Title>
				</Modal.Header>
				<Modal.Body>
					<ControlLabel>Contact name</ControlLabel>
					<FormControl type="text"/>
				</Modal.Body>
				<Modal.Footer>
					<Button>Add</Button>
					{' '}
					<Button onClick={this.props.hide}>Cancel</Button>
				</Modal.Footer>
			</Modal>
		);
	}
}

ReactDOM.render(<People/>, document.getElementById('app'));