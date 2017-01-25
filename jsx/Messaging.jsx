import React from 'react';
import ReactDOM from 'react-dom';
import MainNavbar, {MainJumbotron} from './MainNavbar.jsx';
import ControlLabel from 'react-bootstrap/lib/ControlLabel';
import FormControl from 'react-bootstrap/lib/FormControl';
import FormGroup from 'react-bootstrap/lib/FormGroup';
import HelpBlock from 'react-bootstrap/lib/HelpBlock';
import Button from 'react-bootstrap/lib/Button';

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
	}
	
	handleSubmit(e) {
		console.log("submit");
		e.preventDefault();
	}
	
	render() {
		return (
			<form onSubmit={this.handleSubmit}>
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