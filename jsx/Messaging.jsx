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
import ButtonToolbar from 'react-bootstrap/lib/ButtonToolbar';

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
		this.deselectAll = this.deselectAll.bind(this);
		this.handleSubjectChange = this.handleSubjectChange.bind(this);
		this.handleMessageChange = this.handleMessageChange.bind(this);
		this.state = {showContacts: false, ignoredContacts: {}, subject: "", message: ""};
		let manager = new LocalStorageManager();
		this.people = manager.getPeople();
		this.integrations = manager.getIntegrations();
	}
	
	handleSubmit(e) {
		for (let contact in this.people) {
			if (!this.state.ignoredContacts[contact]) {
				for (let receiver of this.people[contact]) {
					let integration = this.integrations[receiver.platformLabel];
					console.log(integration);
					console.log(receiver);
				}
			}
		}
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
					<Checkbox inline onChange={() => this.handleToggleContact(contact)} checked={!this.state.ignoredContacts[contact]}>
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
				<ButtonToolbar>
					<Button className="pull-right" onClick={this.deselectAll}>Deselect all</Button>
					<Button className="pull-right" onClick={() => this.setState({ignoredContacts: {}})}>Select all</Button>
				</ButtonToolbar>
			</Well>
		);
	}
	
	handleToggleContact(contact) {
		let ignoredContacts = JSON.parse(JSON.stringify(this.state.ignoredContacts));
		ignoredContacts[contact] = !ignoredContacts[contact];
		this.setState({ignoredContacts: ignoredContacts});
	}
	
	deselectAll() {
		let ignoredContacts = {};
		for (let contact in this.people) {
			ignoredContacts[contact] = true;
		}
		this.setState({ignoredContacts: ignoredContacts});
	}
	
	handleSubjectChange(e) {
		this.setState({subject: e.target.value});
	}
	
	handleMessageChange(e) {
		this.setState({message: e.target.value});
	}
	
	render() {
		let error = Object.keys(this.people).length == 0;
		let warning = null;
		if (error) {
			warning = (
				<FormGroup validationState="error">
					<HelpBlock>You must <a href="people.html">create contacts</a> before sending messages.</HelpBlock>
				</FormGroup>
			);
		}
		let contacts = this.state.showContacts ? this.createContacts() : null;
		return (
			<form onSubmit={this.handleSubmit}>
				{warning}
				<FormGroup>
					<ControlLabel>To</ControlLabel>
					<FormControl componentClass="select" placeholder="select" onChange={this.handleChange} disabled={error}>
						<option value="all">All contacts</option>
						<option value="custom">Custom list of contacts</option>
					</FormControl>
					{contacts}
				</FormGroup>
				<FormGroup>
					<ControlLabel>Subject</ControlLabel>
					<FormControl type="text" onChange={this.handleSubjectChange}/>
				</FormGroup>
				<FormGroup>
					<ControlLabel>Message</ControlLabel>
					<FormControl componentClass="textarea" rows="5" onChange={this.handleMessageChange} required/>
				</FormGroup>
				<HelpBlock>Subject will only be used in platforms that support it.</HelpBlock>
				<Button type="submit" className="pull-right" disabled={error}>Send</Button>
			</form>
		);
	}
}

ReactDOM.render(<Messaging/>, document.getElementById('app'));