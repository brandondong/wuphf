import React from 'react';
import ReactDOM from 'react-dom';
import MainNavbar, {MainJumbotron} from './MainNavbar.jsx';
import LocalStorageManager from './LocalStorageManager.js';
import PageHeader from 'react-bootstrap/lib/PageHeader';
import Button from 'react-bootstrap/lib/Button';
import Modal from 'react-bootstrap/lib/Modal';
import ControlLabel from 'react-bootstrap/lib/ControlLabel';
import FormControl from 'react-bootstrap/lib/FormControl';

class People extends React.Component {
	
	constructor() {
		super();
		this.hideModal = this.hideModal.bind(this);
		this.addContact = this.addContact.bind(this);
		let manager = new LocalStorageManager();
		this.state = {showModal: false, people: manager.getPeople()};
	}
	
	hideModal() {
		this.setState({showModal: false});
	}
	
	addContact(s) {
		let people = JSON.parse(JSON.stringify(this.state.people));
		people[s] = [];
		this.setState({people: people});
		new LocalStorageManager().savePeople(people);
	}
	
	render() {
		return (
			<div>
				<MainNavbar/>
				<MainJumbotron title="People" message="Filler for now"/>
				<div className="container">
					<ContactsSection people={this.state.people}/>
					<Button bsSize="large" onClick={() => this.setState({showModal: true})}>Add contact</Button>
				</div>
				<ContactModal showModal={this.state.showModal} hide={this.hideModal} add={this.addContact} people={this.state.people}/>
			</div>
		);
	}
}

class ContactsSection extends React.Component {
	render() {
		let peoplejsx = [];
		for (let name in this.props.people) {
			peoplejsx.push(
				<div key={name}>
					<p>{name}</p>
				</div>
			);
		}
		return (
			<div>
				<PageHeader>Contacts added</PageHeader>
				{peoplejsx}
			</div>
		);
	}
}

class ContactModal extends React.Component {
	
	constructor() {
		super();
		this.handleChange = this.handleChange.bind(this);
		this.add = this.add.bind(this);
		this.state = {value: null};
	}
	
	handleChange(e) {
		this.setState({value: e.target.value});
	}
	add() {
		this.props.hide();
		this.props.add(this.state.value);
	}
	
	render() {
		return (
			<Modal show={this.props.showModal} onHide={this.props.hide}>
				<Modal.Header closeButton>
					<Modal.Title>Add contact</Modal.Title>
				</Modal.Header>
				<Modal.Body>
					<ControlLabel>Contact name</ControlLabel>
					<FormControl type="text" onChange={this.handleChange}/>
				</Modal.Body>
				<Modal.Footer>
					<Button onClick={this.add}>Add</Button>
					{' '}
					<Button onClick={this.props.hide}>Cancel</Button>
				</Modal.Footer>
			</Modal>
		);
	}
}

ReactDOM.render(<People/>, document.getElementById('app'));