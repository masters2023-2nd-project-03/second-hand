//
//  SignInButton.swift
//  Second-Hand
//
//  Created by PJB on 2023/06/27.
//

import UIKit

final class SignInButton: UIButton {
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.setupLoginButtonLayer()
        self.setupLoginButtonTitle()
        self.setupLoginButtonAppearance()
        self.setupLoginButtonConstraint()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.setupLoginButtonLayer()
        self.setupLoginButtonTitle()
        self.setupLoginButtonAppearance()
        self.setupLoginButtonConstraint()
    }
}

extension SignInButton {
    private func setupLoginButtonLayer() {
        self.layer.cornerRadius = Constant.Layout.cornerRadius
        self.clipsToBounds = true
    }
    
    private func setupLoginButtonTitle() {
        self.setTitle(Constant.StringLiteral.title, for: .normal)
    }
    
    private func setupLoginButtonAppearance() {
        self.titleLabel?.font = FontStyle.title3
        self.tintColor = ColorPalette.white
        self.backgroundColor = ColorPalette.black
    }
    
    private func setupLoginButtonConstraint() {
        NSLayoutConstraint.activate(
            [
                self.heightAnchor.constraint(equalToConstant: Constant.Layout.height)
            ]
        )
    }
}
