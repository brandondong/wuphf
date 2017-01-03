import React from 'react';
import MainNavbar from './MainNavbar.jsx';

export default class App extends React.Component {
	render() {
		return (
			<div>
				<MainNavbar/>
				<div className="container">Hello</div>
			</div>
		);
	}
}