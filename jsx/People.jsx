import React from 'react';
import ReactDOM from 'react-dom';
import MainNavbar, {MainJumbotron} from './MainNavbar.jsx';
import LocalStorageManager from './LocalStorageManager.js';
import IntegrationWebService from './IntegrationWebService.js';
import PageHeader from 'react-bootstrap/lib/PageHeader';
import Button from 'react-bootstrap/lib/Button';
import Modal from 'react-bootstrap/lib/Modal';
import ControlLabel from 'react-bootstrap/lib/ControlLabel';
import FormControl from 'react-bootstrap/lib/FormControl';
import FormGroup from 'react-bootstrap/lib/FormGroup';
import HelpBlock from 'react-bootstrap/lib/HelpBlock';
import Panel from 'react-bootstrap/lib/Panel';
import Collapse from 'react-bootstrap/lib/Collapse';
import Well from 'react-bootstrap/lib/Well';
import Col from 'react-bootstrap/lib/Col';
import Row from 'react-bootstrap/lib/Row';
import Image from 'react-bootstrap/lib/Image';

class People extends React.Component {
	
	constructor() {
		super();
		this.hideContactModal = this.hideContactModal.bind(this);
		this.hideCreateModal = this.hideCreateModal.bind(this);
		this.addContact = this.addContact.bind(this);
		this.handleCreate = this.handleCreate.bind(this);
		let manager = new LocalStorageManager();
		this.state = {showContactModal: false, showCreateModal: false, people: manager.getPeople(), platforms: [], createName: null, createPlatform: null};
		new IntegrationWebService().getPlatforms().then((platforms) => {
			this.setState({platforms: platforms});
		});
	}
	
	hideContactModal() {
		this.setState({showContactModal: false});
	}
	
	hideCreateModal() {
		this.setState({showCreateModal: false});
	}
	
	addContact(s) {
		let people = JSON.parse(JSON.stringify(this.state.people));
		people[s] = [];
		this.setState({people: people});
		new LocalStorageManager().savePeople(people);
	}
	
	handleCreate(name, platform) {
		this.setState({showCreateModal: true, createName: name, createPlatform: platform});
	}
	
	render() {
		return (
			<div>
				<MainNavbar/>
				<MainJumbotron title="People" message="Filler for now"/>
				<div className="container">
					<ContactsSection people={this.state.people} platforms={this.state.platforms} create={this.handleCreate}/>
					<Button bsSize="large" onClick={() => this.setState({showContactModal: true})}>Add contact</Button>
				</div>
				<ContactModal showModal={this.state.showContactModal} hide={this.hideContactModal} add={this.addContact} people={this.state.people}/>
				<CreateIntegrationModal showModal={this.state.showCreateModal} hide={this.hideCreateModal} name={this.state.createName} platform={this.state.createPlatform}/>
			</div>
		);
	}
}

class ContactsSection extends React.Component {
	
	constructor() {
		super();
		this.state = {openPanels: {}};
	}
	
	togglePanel(name) {
		let isOpen = this.state.openPanels[name];
		let openPanels = JSON.parse(JSON.stringify(this.state.openPanels));
		openPanels[name] = !isOpen;
		this.setState({openPanels: openPanels});
	}
	
	createIntegrations(name) {
		let platforms = this.props.platforms.map((platform) => {
			let imagePath = "images/" + platform.logo;
			return (
				<Col xs={3} md={2} key={platform.label}>
					<Image src={imagePath} width={57} height={57} className="cursor-pointer" onClick={() => this.props.create(name, platform)} thumbnail/>
					<p>{platform.label}</p>
				</Col>
			);
		});
		return (
			<Row>{platforms}</Row>
		);
	}
	
	render() {
		let peoplejsx = [];
		for (let name in this.props.people) {
			peoplejsx.push(
				<div key={name}>
					<Panel onClick={() => this.togglePanel(name)} className="cursor-pointer">
						{name}
					</Panel>
					<Collapse in={this.state.openPanels[name]}>
					<div><Well>
						<h3>Existing integrations</h3>
						<h3>Create a new integration</h3>
						{this.createIntegrations(name)}
					</Well></div>
					</Collapse>
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
		this.hide = this.hide.bind(this);
		this.state = {value: ""};
	}
	
	handleChange(e) {
		this.setState({value: e.target.value});
	}
	add() {
		this.props.hide();
		this.props.add(this.state.value);
		this.setState({value: ""});
	}
	
	hide() {
		this.props.hide();
		this.setState({value: ""});
	}
	
	render() {
		let error = this.props.people[this.state.value] != null;
		let validationState = error ? "error" : null;
		let errorText = error ? "A contact with this name already exists." : null;
		return (
			<Modal show={this.props.showModal} onHide={this.hide}>
				<Modal.Header closeButton>
					<Modal.Title>Add contact</Modal.Title>
				</Modal.Header>
				<Modal.Body>
					<FormGroup validationState={validationState}>
						<ControlLabel>Contact name</ControlLabel>
						<FormControl type="text" onChange={this.handleChange}/>
						<HelpBlock>{errorText}</HelpBlock>
					</FormGroup>
				</Modal.Body>
				<Modal.Footer>
					<Button onClick={this.add} disabled={error || this.state.value.length == 0}>Add</Button>
					{' '}
					<Button onClick={this.hide}>Cancel</Button>
				</Modal.Footer>
			</Modal>
		);
	}
}

class CreateIntegrationModal extends React.Component {
	render() {
		if (this.props.name == null) {
			return null;
		}
		return (
			<Modal show={this.props.showModal} onHide={this.props.hide}>
				<Modal.Header closeButton>
					<Modal.Title>Create a new integration</Modal.Title>
				</Modal.Header>
				<Modal.Body>
					<p>{this.props.name}</p>
					<p>{this.props.platform.label}</p>
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