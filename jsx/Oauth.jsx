import React from 'react';
import ReactDOM from 'react-dom';
import MainNavbar, {IntegrationsJumbotron} from './MainNavbar.jsx';
import LocalStorageManager from './LocalStorageManager.js';
import IntegrationWebService from './IntegrationWebService.js';
import Button from 'react-bootstrap/lib/Button';

class OAuth extends React.Component {
	
	constructor() {
		super();
		this.state = {progress: "Processing authentication and creating integration. Please wait a moment ...", error: null, description: null};
		
		let map = {};
		let params = window.location.search.substring(1).split("&");
		for (let param of params) {
			let index = param.indexOf("=");
			map[param.substring(0, index)] = param.substring(index + 1);
		}
		let platformLabel = map.state;
		if (map.error) {
			this.state = {progress: "Failed to grant permissions.", error: map.error};
		} else {
			new IntegrationWebService().createIntegration(platformLabel, map.code).then((i) => {
				this.setState({progress: "Integration created successfully."});
				let manager = new LocalStorageManager();
				manager.saveIntegration(i);
				return this.displayPlatformDescription(i);
			}).catch((e) => {
				this.setState({progress: "An unexpected error occurred.", error: e});
			});
		}
	}
	
	displayPlatformDescription(i) {
		return new IntegrationWebService().getPlatforms().then((platforms) => {
			for (let platform of platforms) {
				if (platform.label === i.platformLabel) {
					this.setState({description: platform.description});
					return;
				}
			}
		});
	}
	
	render() {
		return (
			<div>
				<MainNavbar/>
				<IntegrationsJumbotron/>
				<div className="container">
					<h1>{this.state.progress}</h1>
					<p>{this.state.error}</p>
					<p>{this.state.description}</p>
					<Button bsSize="large" href="integrations.html">Back to main integrations page</Button>
				</div>
			</div>
		);
	}
}

ReactDOM.render(<OAuth/>, document.getElementById('app'));