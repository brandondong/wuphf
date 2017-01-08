import React from 'react';
import ReactDOM from 'react-dom';
import MainNavbar, {IntegrationsJumbotron} from './MainNavbar.jsx';
import LocalStorageManager from './LocalStorageManager.js';
import Button from 'react-bootstrap/lib/Button';

class OAuth extends React.Component {
	
	constructor() {
		super();
		this.state = {progress: "Processing authentication and creating integration ..."};
		
		let map = {};
		let params = window.location.search.substring(1).split("&");
		for (let param of params) {
			let index = param.indexOf("=");
			map[param.substring(0, index)] = param.substring(index + 1);
		}
		let platformLabel = new LocalStorageManager().getCurrentPlatform();
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