import React from 'react';
import ReactDOM from 'react-dom';
import MainNavbar, {MainJumbotron} from './MainNavbar.jsx';
import LocalStorageManager from './LocalStorageManager.js';
import ControlLabel from 'react-bootstrap/lib/ControlLabel';
import FormControl from 'react-bootstrap/lib/FormControl';
import FormGroup from 'react-bootstrap/lib/FormGroup';
import HelpBlock from 'react-bootstrap/lib/HelpBlock';
import Button from 'react-bootstrap/lib/Button';
import Checkbox from 'react-bootstrap/lib/Checkbox';
import Well from 'react-bootstrap/lib/Well';
import Row from 'react-bootstrap/lib/Row';
import Col from 'react-bootstrap/lib/Col';

class Messaging extends React.Component {
	render() {
		return (
			<div>
				<MainNavbar/>
				<MainJumbotron title="Messaging" message="Filler for now"/>
				<div className="container">
					<MessagingForm/>
				</div>
			</div>
		);
	}
}

class MessagingForm extends React.Component {
	
	constructor() {
		super();
		this.handleSubmit = this.handleSubmit.bind(this);
		this.handleChange = this.handleChange.bind(this);
		this.state = {showContacts: false};
		let manager = new LocalStorageManager();
		this.people = manager.getPeople()
	}
	
	handleSubmit(e) {
		console.log("submit");
		e.preventDefault();
	}
	
	handleChange(e) {
		let show = e.target.value == "custom";
		this.setState({showContacts: show});
	}
	
	createContacts() {
		let peopleJsx = [];
		for (let contact in this.people) {
			peopleJsx.push(
				<Col xs={3} md={2} key={contact}>
					<Checkbox inline onChange={() => this.handleToggleContact(contact)}>
						{contact}
					</Checkbox>
				</Col>
			);
		}
		return (
			<Well>
				<Row>
				{peopleJsx}
				</Row>
			</Well>
		);
	}
	
	handleToggleContact(contact) {
		console.log(contact);
	}
	
	render() {
		let contacts = this.state.showContacts ? this.createContacts() : null;
		return (
			<form onSubmit={this.handleSubmit}>
				<FormGroup>
					<ControlLabel>To</ControlLabel>
					<FormControl componentClass="select" placeholder="select" onChange={this.handleChange}>
						<option value="all">All contacts</option>
						<option value="custom">Custom list of contacts</option>
					</FormControl>
					{contacts}
				</FormGroup>
				<FormGroup>
					<ControlLabel>Subject</ControlLabel>
					<FormControl type="text"/>
				</FormGroup>
				<FormGroup>
					<ControlLabel>Message</ControlLabel>
					<FormControl componentClass="textarea" rows="5" required/>
				</FormGroup>
				<HelpBlock>Subject will only be used in platforms that support it.</HelpBlock>
				<Button type="submit" className="pull-right">Send</Button>
			</form>
		);
	}
}

ReactDOM.render(<Messaging/>, document.getElementById('app'));