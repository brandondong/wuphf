import React from 'react';
import ReactDOM from 'react-dom';
import MainNavbar, {MainJumbotron} from './MainNavbar.jsx';
import LocalStorageManager from './LocalStorageManager.js';
import PageHeader from 'react-bootstrap/lib/PageHeader';

class Integrations extends React.Component {
	render() {
		return (
			<div>
				<MainNavbar/>
				<MainJumbotron title="Integrations" message="Filler for now"/>
				<div className="container">
					<ExistingIntegrationsSection/>
				</div>
			</div>
		);
	}
}

class ExistingIntegrationsSection extends React.Component {
	
	constructor() {
		super();
		let manager = new LocalStorageManager();
		this.state = {integrations: manager.getIntegrations()};
	}
	
	render() {
		let content = "None";
		if (this.state.integrations.length > 0) {
			content = "Got some";
		}
		return (
			<div>
				<PageHeader>Existing Integrations</PageHeader>
				{content}
			</div>
		);
	}
}

ReactDOM.render(<Integrations/>, document.getElementById('app'));