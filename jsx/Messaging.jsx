import React from 'react';
import ReactDOM from 'react-dom';
import MainNavbar, {MainJumbotron} from './MainNavbar.jsx';
import LocalStorageManager from './LocalStorageManager.js';
import ControlLabel from 'react-bootstrap/lib/ControlLabel';
import FormControl from 'react-bootstrap/lib/FormControl';
import FormGroup from 'react-bootstrap/lib/FormGroup';
import HelpBlock from 'react-bootstrap/lib/HelpBlock';
import Button from 'react-bootstrap/lib/Button';
import DropdownButton from 'react-bootstrap/lib/DropdownButton';
import MenuItem from 'react-bootstrap/lib/MenuItem';
import Checkbox from 'react-bootstrap/lib/Checkbox';

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
		let manager = new LocalStorageManager();
		this.people = manager.getPeople()
	}
	
	handleSubmit(e) {
		console.log("submit");
		e.preventDefault();
	}
	
	handleMenuItem(contact) {
		console.log(contact);
	}
	
	render() {
		let peopleJsx = [];
		for (let contact in this.people) {
			peopleJsx.push(
				<MenuItem key={contact} onClick={() => this.handleMenuItem(contact)}>
					<Checkbox inline>
						{contact}
					</Checkbox>
				</MenuItem>
			);
		}
		return (
			<form onSubmit={this.handleSubmit}>
				<FormGroup>
					<DropdownButton title="To" id="dropdown">
						{peopleJsx}
					</DropdownButton>
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