import React from 'react';
import ReactDOM from 'react-dom';
import MainNavbar, {MainJumbotron} from './MainNavbar.jsx';

class Integrations extends React.Component {
	render() {
		return (
			<div>
				<MainNavbar/>
				<MainJumbotron title="Integrations" message="Filler for now"/>
				<div className="container">Integrations</div>
			</div>
		);
	}
}

ReactDOM.render(<Integrations/>, document.getElementById('app'));