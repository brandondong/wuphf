import React from 'react';
import ReactDOM from 'react-dom';
import MainNavbar, {MainJumbotron} from './MainNavbar.jsx';
import LocalStorageManager from './LocalStorageManager.js';
import PageHeader from 'react-bootstrap/lib/PageHeader';
import Row from 'react-bootstrap/lib/Row';
import Col from 'react-bootstrap/lib/Col';
import Image from 'react-bootstrap/lib/Image';
import IntegrationWebService from './IntegrationWebService.js';

class Integrations extends React.Component {
	
	constructor() {
		super();
		let manager = new LocalStorageManager();
		this.state = {integrations: manager.getIntegrations()};
	}
	
	render() {
		return (
			<div>
				<MainNavbar/>
				<MainJumbotron title="Integrations" message="Filler for now"/>
				<div className="container">
					<ExistingIntegrationsSection integrations={this.state.integrations}/>
					<NewIntegrationSection integrations={this.state.integrations}/>
				</div>
			</div>
		);
	}
}

class ExistingIntegrationsSection extends React.Component {
	render() {
		let content = "None";
		if (this.props.integrations.length > 0) {
			content = "Got some";
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
	
	constructor() {
		super();
		this.state = {platforms: []};
		new IntegrationWebService().getPlatforms().then((platforms) => {
			this.setState({platforms: platforms});
		});
	}
	
	render() {
		let platforms = this.state.platforms.map(function (platform) {
			let imagePath = "images/" + platform.logo;
			return (
				<Col xs={3} md={2} key={platform.label}>
					<a href={platform.redirectUrl}>
						<Image src={imagePath} width={57} height={57} thumbnail/>
					</a>
					<a href={platform.redirectUrl}>
						<p>{platform.label}</p>
					</a>
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