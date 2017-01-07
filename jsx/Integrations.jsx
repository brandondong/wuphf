import React from 'react';
import ReactDOM from 'react-dom';
import MainNavbar, {MainJumbotron} from './MainNavbar.jsx';
import LocalStorageManager from './LocalStorageManager.js';
import PageHeader from 'react-bootstrap/lib/PageHeader';
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
			return <p>{platform.label}</p>
		});
		return (
			<div>
				<PageHeader>Create a new integration</PageHeader>
				{platforms}
			</div>
		);
	}
}

ReactDOM.render(<Integrations/>, document.getElementById('app'));