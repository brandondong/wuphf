import React from 'react';
import ReactDOM from 'react-dom';
import MainNavbar, {MainJumbotron} from './MainNavbar.jsx';

class People extends React.Component {
	render() {
		return (
			<div>
				<MainNavbar/>
				<MainJumbotron title="People" message="Filler for now"/>
				<div className="container">People</div>
			</div>
		);
	}
}

ReactDOM.render(<People/>, document.getElementById('app'));