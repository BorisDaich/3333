import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ICatalogcolors } from '../catalogcolors.model';
import { CatalogcolorsService } from '../service/catalogcolors.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  standalone: true,
  templateUrl: './catalogcolors-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class CatalogcolorsDeleteDialogComponent {
  catalogcolors?: ICatalogcolors;

  constructor(protected catalogcolorsService: CatalogcolorsService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.catalogcolorsService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
