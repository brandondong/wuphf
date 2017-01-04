import React from 'react';
import ReactDOM from 'react-dom';
import MainNavbar from './MainNavbar.jsx';

class Integrations extends React.Component {
	render() {
		return (
			<div>
				<MainNavbar/>
				<div className="container">Integrations</div>
			</div>
		);
	}
}

ReactDOM.render(<Integrations/>, document.getElementById('app'));