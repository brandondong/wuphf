import React from 'react';
import ReactDOM from 'react-dom';
import MainNavbar, {IntegrationsJumbotron} from './MainNavbar.jsx';
import LocalStorageManager from './LocalStorageManager.js';
import IntegrationWebService from './IntegrationWebService.js';
import Button from 'react-bootstrap/lib/Button';

class OAuth extends React.Component {
	
	constructor() {
		super();
		this.state = {progress: "Processing authentication and creating integration. Please wait a moment ..."};
		
		let map = {};
		let params = window.location.search.substring(1).split("&");
		for (let param of params) {
			let index = param.indexOf("=");
			map[param.substring(0, index)] = param.substring(index + 1);
		}
		let platformLabel = new LocalStorageManager().getCurrentPlatform();
		new IntegrationWebService().createIntegration(platformLabel, map).then((i) => {
			this.setState({progress: "Integration created successfully."});
		}).catch((e) => {
			this.setState({progress: "Error!"});
		});
	}
	
	render() {
		return (
			<div>
				<MainNavbar/>
				<IntegrationsJumbotron/>
				<div className="container">
					<h1>{this.state.progress}</h1>
					<Button bsSize="large" href="integrations.html">Back to main integrations page</Button>
				</div>
			</div>
		);
	}
}

ReactDOM.render(<OAuth/>, document.getElementById('app'));