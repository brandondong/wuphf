import React from 'react';
import Navbar from 'react-bootstrap/lib/Navbar';
import NavItem from 'react-bootstrap/lib/NavItem';
import Nav from 'react-bootstrap/lib/Nav';

export default class MainNavbar extends React.Component {
	render() {
		return (
			<Navbar>
				<Navbar.Header>
					<Navbar.Brand>
						<a>Wuphf</a>
					</Navbar.Brand>
				</Navbar.Header>
					<Nav>
						<NavItem eventKey={1}>Integrations</NavItem>
						<NavItem eventKey={1}>People</NavItem>
						<NavItem eventKey={1}>Messaging</NavItem>
					</Nav>
			</Navbar>
		);
	}
}