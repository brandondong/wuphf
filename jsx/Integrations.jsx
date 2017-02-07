import React from 'react';
import ReactDOM from 'react-dom';
import MainNavbar, {IntegrationsJumbotron} from './MainNavbar.jsx';
import LocalStorageManager from './LocalStorageManager.js';
import PageHeader from 'react-bootstrap/lib/PageHeader';
import Row from 'react-bootstrap/lib/Row';
import Col from 'react-bootstrap/lib/Col';
import Image from 'react-bootstrap/lib/Image';
import Button from 'react-bootstrap/lib/Button';
import Popover from 'react-bootstrap/lib/Popover';
import OverlayTrigger from 'react-bootstrap/lib/OverlayTrigger';
import IntegrationWebService from './IntegrationWebService.js';

class Integrations extends React.Component {
	
	constructor() {
		super();
		let manager = new LocalStorageManager();
		this.state = {integrations: manager.getIntegrations(), platforms: []};
		this.deleteIntegration = this.deleteIntegration.bind(this);
		new IntegrationWebService().getPlatforms().then((platforms) => {
			this.setState({platforms: platforms});
		});
	}
	
	deleteIntegration(integration) {
		let integrations = JSON.parse(JSON.stringify(this.state.integrations));
		delete integrations[integration.platformLabel];
		this.setState({integrations: integrations})
		new LocalStorageManager().saveIntegrations(integrations);
	}
	
	render() {
		return (
			<div>
				<MainNavbar/>
				<IntegrationsJumbotron/>
				<div className="container">
					<ExistingIntegrationsSection integrations={this.state.integrations} platforms={this.state.platforms} deleteHandler={this.deleteIntegration}/>
					<NewIntegrationSection integrations={this.state.integrations} platforms={this.state.platforms}/>
				</div>
			</div>
		);
	}
}

class ExistingIntegrationsSection extends React.Component {
	
	createIntegrationDisplay(platform, integration) {
		let imagePath = "images/" + platform.logo;
		let popover = (
			<Popover id="popover-trigger-click-root-close" title="Options">
				<Button href={platform.redirectUrl}>Edit</Button>
				{' '}
				<Button bsStyle="danger" onClick={() => this.props.deleteHandler(integration)}>Delete</Button>
			</Popover>
		);
		return (
			<Col xs={3} md={2} key={platform.label}>
				<OverlayTrigger trigger="click" rootClose placement="top" overlay={popover}>
					<Image src={imagePath} width={57} height={57} className="cursor-pointer" thumbnail/>
				</OverlayTrigger>
				<p>{integration.idField}</p>
			</Col>
		);
	}
	
	render() {
		let integrations = Object.values(this.props.integrations);
		let platformMap = {};
		for (let platform of this.props.platforms) {
			platformMap[platform.label] = platform;
		}
		let content = "No integrations have been created yet.";
		if (integrations.length > 0) {
			let jsx = [];
			for (let integration of integrations) {
				let platform = platformMap[integration.platformLabel];
				if (platform != null) {
					jsx.push(this.createIntegrationDisplay(platform, integration));
				}
			}
			content = <Row>{jsx}</Row>;
		}
		return (
			<div>
				<PageHeader>Existing integrations</PageHeader>
				{content}
			</div>
		);
	}
}

class NewIntegrationSection extends React.Component {
	
	wrapInPlatformLink(platform, jsx) {
		return (
			<a href={platform.redirectUrl}>
				{jsx}
			</a>
		);
	}
	
	render() {
		let platforms = this.props.platforms.filter((platform) => {
			return this.props.integrations[platform.label] == null;
		}).map((platform) => {
			let imagePath = "images/" + platform.logo;
			let logo = this.wrapInPlatformLink(platform,
				<Image src={imagePath} width={57} height={57} thumbnail/>
			);
			let label = this.wrapInPlatformLink(platform,
				<p>{platform.label}</p>
			);
			return (
				<Col xs={3} md={2} key={platform.label}>
					{logo}
					{label}
				</Col>
			);
		});
		return (
			<div>
				<PageHeader>Create a new integration</PageHeader>
				<Row>
					{platforms}
				</Row>
			</div>
		);
	}
}

ReactDOM.render(<Integrations/>, document.getElementById('app'));