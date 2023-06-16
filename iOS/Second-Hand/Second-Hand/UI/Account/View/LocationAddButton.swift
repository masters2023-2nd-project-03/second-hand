//
//  locationAddButton.swift
//  Second-Hand
//
//  Created by PJB on 2023/06/14.
//

import UIKit

class LocationAddButton: UIButton {
    private let plusIamge = UIImage(systemName: "plus")
    private let buttonTitle = Constant.StringLiteral.title
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.setupLocationButtonConfiguration()
        self.setupButtonLayoutContraint()
        self.setupLocationAddButtonLayer()
        self.setupLocationAddButtonContent()
        self.setupLocationAddButtonAppearance()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.setupLocationButtonConfiguration()
        self.setupButtonLayoutContraint()
        self.setupLocationAddButtonLayer()
        self.setupLocationAddButtonContent()
        self.setupLocationAddButtonAppearance()
    }
}

extension LocationAddButton {
    private func setupLocationButtonConfiguration() {
        let imagePadding: CGFloat = 4
        self.configuration = .plain()
        self.configuration?.imagePadding = imagePadding
    }
    
    private func setupLocationAddButtonLayer() {
        self.layer.borderColor = ColorPalette.gray500?.cgColor
        self.layer.borderWidth = 1
        self.layer.cornerRadius = 8
        self.clipsToBounds = true
    }
    
    private func setupLocationAddButtonContent() {
        self.setTitle(self.buttonTitle, for: .normal)
        self.setImage(self.plusIamge, for: .normal)
    }
    
    private func setupLocationAddButtonAppearance() {
        self.titleLabel?.font = FontStyle.subHead
        self.tintColor = ColorPalette.black
    }
    
    private func setupButtonLayoutContraint() {
        let height: CGFloat = 52
        NSLayoutConstraint.activate(
            [
                self.heightAnchor.constraint(equalToConstant: height)
            ]
        )
    }
}
