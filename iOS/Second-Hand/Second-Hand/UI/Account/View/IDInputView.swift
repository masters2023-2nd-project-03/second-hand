//
//  IDInputView.swift
//  Second-Hand
//
//  Created by PJB on 2023/06/13.
//

import UIKit

final class IDInputView: UIView {
    private let idLabel: UILabel = {
        let label = UILabel()
        label.text = Constant.StringLiteral.idLabel
        label.font = FontStyle.body
        return label
    }()
    
    private let idTextField: UITextField = {
        let textField = UITextField()
        textField.placeholder = Constant.StringLiteral.placeholder
        return textField
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.addSubViews()
        self.setupIDLabelLayoutConstraint()
        self.setupIDTextFieldLayoutConstraint()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.addSubViews()
        self.setupIDLabelLayoutConstraint()
        self.setupIDTextFieldLayoutConstraint()
    }
}

extension IDInputView {
    private func addSubViews() {
        self.addSubview(idLabel)
        self.addSubview(idTextField)
    }
    
    // swiftlint:disable:next function_body_length
    private func setupIDLabelLayoutConstraint() {
        let labelLeadingPadding: CGFloat = 16
        let labelWidth: CGFloat = 47
        
        self.idLabel.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate(
            [
                self.idLabel.topAnchor.constraint(equalTo: self.topAnchor),
                self.idLabel.leadingAnchor.constraint(
                    equalTo: self.leadingAnchor,
                    constant: labelLeadingPadding
                ),
                self.idLabel.centerYAnchor.constraint(equalTo: self.centerYAnchor),
                self.idLabel.widthAnchor.constraint(equalToConstant: labelWidth)
            ]
        )
    }
    
    // swiftlint:disable:next function_body_length
    private func setupIDTextFieldLayoutConstraint() {
        let textFieldLeadingPadding: CGFloat = 52
        let textFieldTrailingPadding: CGFloat = -16
        
        self.idTextField.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate(
            [
                self.idTextField.topAnchor.constraint(equalTo: self.topAnchor),
                self.idTextField.leadingAnchor.constraint(
                    equalTo: self.idLabel.trailingAnchor,
                    constant: textFieldLeadingPadding
                ),
                self.idTextField.trailingAnchor.constraint(
                    equalTo: self.trailingAnchor,
                    constant: textFieldTrailingPadding
                ),
                self.idTextField.centerYAnchor.constraint(equalTo: self.centerYAnchor)
            ]
        )
    }
}
