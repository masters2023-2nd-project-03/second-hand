//
//  SignInViewController.swift
//  Second-Hand
//
//  Created by PJB on 2023/06/27.
//

import UIKit

final class SignInViewController: UIViewController {
    private let navigationBar = UINavigationBar()
    private let idInputView = IDInputView()
    private let signInButton = SignInButton()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.setupSignInViewControllerAppearance()
        self.addSubViews()
        self.setupNavigationBarUI()
        self.setupComponentsLayoutConstraint()
        self.presentSignUpViewController()
    }
}

extension SignInViewController {
    private func addSubViews() {
        self.view.addSubview(self.navigationBar)
        self.view.addSubview(self.idInputView)
        self.view.addSubview(self.signInButton)
    }
    
    private func setupNavigationBarUI() {
        self.setupNavigationBarApppearance()
        self.setupNavigationBarTitle()
    }
    
    private func setupComponentsLayoutConstraint() {
        self.setupNavigationBarLayoutConstraint()
        self.setupIDInputVuewLayoutConstraint()
        self.setupSignInButtonLayoutConstraint()
    }
    
    private func setupSignInViewControllerAppearance() {
        self.view.backgroundColor = ColorPalette.white
    }
    
    private func setupNavigationBarApppearance() {
        self.navigationBar.barTintColor = ColorPalette.white
    }
    
    private func setupNavigationBarTitle() {
        self.navigationItem.title = Constant.StringLiteral.title
        self.navigationBar.setItems([self.navigationItem], animated: false)
    }
    
    private func setupNavigationBarLayoutConstraint() {
        self.navigationBar.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate(
            [
                self.navigationBar.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor),
                self.navigationBar.leadingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.leadingAnchor),
                self.navigationBar.trailingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.trailingAnchor)
            ]
        )
    }
    
    // swiftlint:disable:next function_body_length
    private func setupIDInputVuewLayoutConstraint() {
        self.idInputView.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate(
            [
                self.idInputView.topAnchor.constraint(
                    equalTo: self.navigationBar.bottomAnchor,
                    constant: Constant.Layout.IdInputView.idInputViewTopInset
                ),
                self.idInputView.leadingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.leadingAnchor),
                self.idInputView.trailingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.trailingAnchor),
                self.idInputView.heightAnchor.constraint(equalToConstant: Constant.Layout.IdInputView.height)
            ]
        )
    }
    
    // swiftlint:disable:next function_body_length
    private func setupSignInButtonLayoutConstraint() {
        self.signInButton.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate(
            [
                self.signInButton.topAnchor.constraint(
                    equalTo: self.idInputView.bottomAnchor,
                    constant: Constant.Layout.SignInButton.signInButtonTopInset
                ),
                self.signInButton.leadingAnchor.constraint(
                    equalTo: self.view.leadingAnchor,
                    constant: Constant.Layout.SignInButton.signInButtonleadingInset
                ),
                self.signInButton.trailingAnchor.constraint(
                    equalTo: self.view.trailingAnchor,
                    constant: Constant.Layout.SignInButton.signInButtonTrailingInset
                )
            ]
        )
    }
    
    private func presentSignUpViewController() {
        let presentAction = UIAction { _ in
            self.present(SignUpViewController(), animated: true)
        }
        
        self.signInButton.addAction(presentAction, for: .touchUpInside)
    }
}
